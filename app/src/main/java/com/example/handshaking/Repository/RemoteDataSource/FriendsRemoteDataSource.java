package com.example.handshaking.Repository.RemoteDataSource;

import com.example.handshaking.Entity.Friend;
import com.example.handshaking.Interfaces.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class FriendsRemoteDataSource {

    private WebService webService;
    private List<Friend> friends;
    private Friend friend;

    public List<Friend> getFriends() {

        webService.getFriends().enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                friends = response.body();
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {

            }
        });

        return friends;
    }

    public Friend getFriendById(String id){


        webService.getFriend(id).enqueue(new Callback<Friend>() {
            @Override
            public void onResponse(Call<Friend> call, Response<Friend> response) {
                friend = response.body();
            }

            @Override
            public void onFailure(Call<Friend> call, Throwable t) {

            }
        });

        return friend;
    }
}
