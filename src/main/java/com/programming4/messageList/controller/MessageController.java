package com.programming4.messageList.controller;

import com.programming4.messageList.data.Message;
import com.programming4.messageList.data.ThreadSafeSQLiteDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GET the page number, get the page, and mapping the user to different pages.
 *
 * <p>{@Controller} is the part of spring MVC.
 * It realizes the functionalities mentioned below:
 * 1. Realize the Page-Separation functionality.
 * 2. Init the {@code message} object.
 * 3. Show the list view to users.
 * 4. Show the post view to users.
 *
 * @author Wu Runfei, LiuYing
 * @since May 5th, 2021
 * </p>
 */
@Controller
@RequestMapping("message")
public class MessageController {
    /**
     * Defining the variables to store the data.
     * <p>Defining {@code List} object to store the message.
     * Defining {@code db} object, and defines the max
     * number of messages for each page.</p>
     */
    private List<Message> messages = new ArrayList<>();
    private ThreadSafeSQLiteDatabase db;
    private final int MAX_PAGE_SIZE = 5;

    /**
     * Get the total number of the page.
     * @return the {@code totalPageNum} to the console.
     */
    private int getTotalPageNum() {
        int totalPageNum = 1;
        if (this.messages.size() > this.MAX_PAGE_SIZE) {
            totalPageNum = this.messages.size() / this.MAX_PAGE_SIZE + 1;
        }
        return totalPageNum;
    }

    /**
     * Add the message to the page.
     * @param pageIndex Number represents the index of the page.
     * @return the content of the {@code page}.
     */
    private List<Message> getPage(int pageIndex) {
        List<Message> page = new ArrayList<>();
        if (this.messages.size() == 0) return page;

        int messageStartIndex = (pageIndex-1) * this.MAX_PAGE_SIZE;
        int messageEndIndex = messageStartIndex + 4;
        if (messageEndIndex > this.messages.size() - 1) {
            messageEndIndex = this.messages.size() - 1;
        }
        for (int i = messageStartIndex; i <= messageEndIndex; i++) {
            page.add(this.messages.get(i));
        }
        return page;
    }

    /**
     * Identifying the {@code page} whether it has the previous or the next page.
     * @param current Number represents the current page index.
     * @return the Result whether it has the previous or the next page.
     */
    private boolean haveNextPage(int current) {
        return current < this.getTotalPageNum();
    }

    private boolean havePrevPage(int current) {
        return current > 1;
    }

    /**
     * Init the {@code database}.
     */
    @PostConstruct
    public void init() {
        try {
            this.db = new ThreadSafeSQLiteDatabase("test.db");
            messages = db.readMessages();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Directing the user to the List Page.
     */
    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list(@RequestParam(
            value="page",
            required = false,
            defaultValue = "1") int page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");

        if (page <= 0) page = 1;
        int totalPages = this.getTotalPageNum();
        if (page > totalPages) page = totalPages;

        mv.getModel().put("messages", this.getPage(page));
        mv.getModel().put("currentPage", page);
        mv.getModel().put("haveNext", this.haveNextPage(page));
        mv.getModel().put("havePrev", this.havePrevPage(page));
        return mv;
    }

    @RequestMapping(value="/post", method= RequestMethod.GET)
    public ModelAndView addForm(RedirectAttributes attr){

        ModelAndView  mv = new ModelAndView("post");
        mv.getModel().put("message", attr.getAttribute("message"));
        return mv;
    }

    /**
     * Saving the data into the list, and stored it into the database.
     * @param allParams which is needed to be parsed into four elements.
     * @return the list page.
     */
    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String save(@RequestParam Map<String, String> allParams){
        String title = allParams.get("title");
        String content = allParams.get("content");
        String sender = allParams.get("sender");
        String URLAddress = allParams.get("URLAddress");

        Message message = new Message(title, content, sender, URLAddress);
        this.db.saveMessage(message);
        this.messages = db.readMessages();

        return "redirect:/message/list";
    }

    @RequestMapping(value="/delete", method= RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> allParams){
        int id = Integer.parseInt(allParams.get("id"));
        String currentPage = allParams.get("currentPage");

        this.db.removeMessage(id);
        this.messages = db.readMessages();
        return "redirect:/message/list?page="+currentPage;
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public  ModelAndView edit(@RequestParam Map<String, String> allParams){
        int id = Integer.parseInt(allParams.get("id"));
        ModelAndView  redirectMv = new ModelAndView("post");
        Message message = null;

        for (Message m : this.messages) {
            if (m.getId() == id) {
                message = m;
                break;
            }
        }

        redirectMv.addObject("message", message);
        return redirectMv;
    }

    @PutMapping(value="/save")
    public String editMessage(@RequestParam Map<String, String> allParams){
        int id = Integer.parseInt(allParams.get("id"));
        String title = allParams.get("title");
        String content = allParams.get("content");
        String sender = allParams.get("sender");
        String URLAddress = allParams.get("URLAddress");

        Message message = new Message(title, content, sender, URLAddress);
        this.db.removeMessage(id);
        this.db.saveMessage(message);
        this.messages = db.readMessages();
        return "redirect:/message/list";
    }
}
