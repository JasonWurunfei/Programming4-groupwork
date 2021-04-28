package com.example.demo;

public class Message {
    private String title;
    private String content;
    private String sender;
    private String URLAddress;

    public Message() {}
    public Message(String title, String content, String sender, String URLAddress) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.URLAddress = URLAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getURLAddress() {
        return URLAddress;
    }

    public void setURLAddress(String URLAddress) {
        this.URLAddress = URLAddress;
    }
}
