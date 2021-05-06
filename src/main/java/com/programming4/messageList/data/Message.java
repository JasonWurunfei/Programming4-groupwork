package com.programming4.messageList.data;

/**
 * Define the {@code Message} Object.
 *
 * <p>{@code Message} object contains four {@code String} attributes,
 * they are title, content, sender, and URLAddress. Besides, this file
 * also has the constructor, getter, and setter for the {@code Message} object.</p>
 *
 * @author Wu Runfei, LiuYing
 * @since May 1st, 2021
 */
public class Message {
    private int id;
    private String title;
    private String content;
    private String sender;
    private String URLAddress;

    /**
     * Constructors for {@code Message} object.
     */
    public Message() {}

    public Message(String title, String content, String sender, String URLAddress) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.URLAddress = URLAddress;
    }

    /**
     * Constructors for Message Object
     */
    public Message(int id, String title, String content, String sender, String URLAddress) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.URLAddress = URLAddress;
    }

    /**
     * Getters, and Setters for four elements.
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
