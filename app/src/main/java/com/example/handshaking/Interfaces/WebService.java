package com.example.handshaking.Interfaces;

import com.example.handshaking.Entity.Friend;
import com.example.handshaking.Entity.FriendshipRequest;
import com.example.handshaking.Entity.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {

    @GET("/friends")
    public Call<List<Friend>> getFriends();
    @GET("/friends/{friend}")
    public Call<Friend> getFriend(@Path("friend") String friendId);
    @POST("/friends/invitation")
    public Call<Response> sendFriendship(@Body FriendshipRequest friendshipRequest);

}
