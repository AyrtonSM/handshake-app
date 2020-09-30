package com.example.handshaking.Repository.RemoteDataSource;

import com.example.handshaking.Entity.Friend;
import com.example.handshaking.Entity.FriendshipRequest;
import com.example.handshaking.Interfaces.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsRemoteDataSource {

    private WebService webService;
    private List<Friend> friends;
    private Friend friend;


    public FriendsRemoteDataSource(){
        this.webService = RetrofitConfig.getConfiguration().create(WebService.class);
    }

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

    public String sendFriendshipRequest(FriendshipRequest friendshipRequest){
        Call<String> friendshipCall =  this.webService.sendFriendship(friendshipRequest);
        String friendshipMessage = null;
        try {
            friendshipCall.timeout().deadline(10, TimeUnit.SECONDS);
            String friendshipResponse = friendshipCall.execute().body();
            try {
                JSONObject friendshipResponseCode = new JSONObject(friendshipResponse);
                if(friendshipResponseCode.getInt("status_code") == 200){
                    friendshipMessage = "Friendship Request Sent to " + friendshipRequest.getFriendContact().getName() ;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            friendshipMessage = "Sorry! It wasn't possible to deliver your request :( I'm trying to find out why";
            e.printStackTrace();
        }

        return friendshipMessage;





    }


}
