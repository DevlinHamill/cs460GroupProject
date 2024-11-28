package com.example.project.model;

import java.util.Date;

/**
 * @author BT&T
 * CS 460
 */
public class ChatMessage {
    /**
     * keeps track of the senders user ID
     */
    public String senderId;
    /**
     * keeps track of the recievers user ID
     */
    public String recieverId;
    /**
     * keeps track of the message being sent
     */
    public String message;
    /**
     * keeps track of the date and time the message will be sent
     */
    public String dateTime;

    /**
     * allows the time to be stored as a date object at first
     */
    public Date dateObject;
}
