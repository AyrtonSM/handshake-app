package com.example.handshaking.Entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @Expose
    @SerializedName(value = "id", alternate = "contactId")
    private int id;
    @Expose
    @SerializedName(value = "name", alternate = "contactName")
    private String name;
    @Expose
    @SerializedName(value = "telephone", alternate = "contactTelephone")
    private String telephone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
