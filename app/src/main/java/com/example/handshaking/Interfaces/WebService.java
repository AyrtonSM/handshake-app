package com.example.handshaking.Interfaces;

import com.example.handshaking.Entity.Friend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("/friends")
    public Call<List<Friend>> getFriends();
    @GET("/friends/{friend}")
    public Call<Friend> getFriend(@Path("friend") String friendId);

}
