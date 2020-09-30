package com.example.handshaking.Model;

import android.util.Log;

import com.example.handshaking.Entity.Contact;

import java.util.ArrayList;
import java.util.Iterator;

public class FriendCollection {

    private static ArrayList<Contact> friends = new ArrayList<>();

    public static void addFriend(Contact contact){

        // SendRequestToContact
        // if the Contact accepts communication, then render ChatWindow

        FriendCollection.friends.add(contact);


    }

    public static Contact getContactById(int id){
        Iterator<Contact> contactIterator = friends.iterator();

        while(contactIterator.hasNext()){
            Contact currentContact = contactIterator.next();
            Log.e("contact",currentContact.getName());

        }

        return null;
    }

}

