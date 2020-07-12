package com.example.handshaking.Entity;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chat implements Serializable {

    private Bitmap userAvatar;
    private String userName;
    private String lastSeenDate;
    private ArrayList<ChatMessage> messages;


    public Chat(){}
    public Chat(Bitmap image, String userName, String lastSeenDate, ArrayList<ChatMessage> messages){
        this.userAvatar = image;
        this.userName = userName;
        this.lastSeenDate = lastSeenDate;
        this.messages = messages;
    }

    public Bitmap getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(Bitmap userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(String lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }
}
