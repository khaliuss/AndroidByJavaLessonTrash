package com.example.messengerbyfirebase;

public class Message {

    private String text;
    private String senderId;
    private String receiverId;

    public Message(String text, String senderText, String receiverId) {
        this.text = text;
        this.senderId = senderText;
        this.receiverId = receiverId;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }
}
