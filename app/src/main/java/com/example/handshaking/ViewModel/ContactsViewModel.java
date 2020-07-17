package com.example.handshaking.ViewModel;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.handshaking.Model.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ContactsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Contact>> liveContactsData;
    private Activity activity;
    public MutableLiveData<ArrayList<Contact>> getContacts(){
        if(this.liveContactsData == null){
            this.liveContactsData = new MutableLiveData<ArrayList<Contact>>();
            loadContacts();
        }

        return this.liveContactsData;
    }
    public void setActivity(Activity activity){
        this.activity = activity;
    }

    private void getFriends(){

    }

    private void loadContacts() {
        // Async Call
        Cursor phones = this.activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null);

        ArrayList<Contact> contacts = new ArrayList<>();

        while(phones.moveToNext()){
            Contact contact = new Contact();

            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contact.setName(name);
            contact.setTelephone(phone);

            contacts.add(contact);
        }

        this.liveContactsData.postValue(contacts);
    }

}
