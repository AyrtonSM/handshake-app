package com.example.handshaking.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendshipRequest {
    @Expose
    @SerializedName(value = "originContact")
    private Contact originContact;
    @Expose
    @SerializedName(value = "friendContact")
    private Contact friendContact;

    public Contact getOriginContact() {
        return originContact;
    }

    public void setOriginContact(Contact originContact) {
        this.originContact = originContact;
    }

    public Contact getFriendContact() {
        return friendContact;
    }

    public void setFriendContact(Contact friendContact) {
        this.friendContact = friendContact;
    }
}
