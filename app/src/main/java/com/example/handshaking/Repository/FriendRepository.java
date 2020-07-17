package com.example.handshaking.Repository;

import com.example.handshaking.Entity.Friend;
import com.example.handshaking.Repository.RemoteDataSource.FriendsRemoteDataSource;

import java.util.List;

public class FriendRepository {

    private FriendsRemoteDataSource friendsRemoteDataSource;

    public List<Friend> getFriends(){
        return this.friendsRemoteDataSource.getFriends();
    }
    public Friend getFriend(Friend friend){

        return this.friendsRemoteDataSource.getFriendById(friend.getId());
    }




}
