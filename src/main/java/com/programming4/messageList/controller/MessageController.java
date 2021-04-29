package com.programming4.messageList.controller;

import com.programming4.messageList.data.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController {
    private List<Message> messages = new ArrayList<>();

    //This method is called automatically by Spring to initialize the data
    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            messages.add(new Message(
                    "Message" + i,
                    "Message" + i,
                    "Jason",
                    "localhost:8080"
            ));
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
        return "redirect:/message/list";
    }
}
