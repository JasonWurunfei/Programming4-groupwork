package com.programming4.messageList.controller;

import com.programming4.messageList.data.Message;
import com.programming4.messageList.data.ThreadSafeSQLiteDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController {
    private List<Message> messages = new ArrayList<>();
    ThreadSafeSQLiteDatabase db;

    @PostConstruct
    public void init() {
        try {
            this.db = new ThreadSafeSQLiteDatabase("test.db");
            messages = db.readMessages();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.getModel().put("messages", messages);
        return mv;
    }

    @RequestMapping(value="/post", method= RequestMethod.GET)
    public String addForm(){
        return "post";
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String save(@RequestParam Map<String, String> allParams){
        String title = allParams.get("title");
        String content = allParams.get("content");
        String sender = allParams.get("sender");
        String URLAddress = allParams.get("URLAddress");
        Message message = new Message(title, content, sender, URLAddress);

        messages.add(message);
        if (this.db != null) this.db.saveMessage(message);
        return "redirect:/message/list";
    }
}
