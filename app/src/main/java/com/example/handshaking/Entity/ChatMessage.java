package com.example.handshaking.Entity;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    private String userName;
    private String message;
    private boolean received;
    private boolean seen;
    private String messageDate;

    public ChatMessage(){}
    public ChatMessage(String userName, String message, boolean received , boolean seen, String messageDate){
        this.userName = userName;
        this.message = message;
        this.received = received;
        this.seen = seen;
        this.messageDate = messageDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
}
