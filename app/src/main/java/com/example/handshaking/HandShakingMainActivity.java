package com.example.handshaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;
import android.net.Uri;
import android.os.Bundle;
import com.example.handshaking.Fragment.CallFragment;
import com.example.handshaking.Fragment.ChatFragment;
import com.example.handshaking.Fragment.ContactsFragment;
import com.example.handshaking.ViewModel.HandShakingViewModel;

public class HandShakingMainActivity extends AppCompatActivity
        implements ContactsFragment.OnFragmentInteractionListener,
                    ChatFragment.OnFragmentInteractionListener ,
                    CallFragment.OnFragmentInteractionListener{


    private FragmentTabHost mTabHost;
    private HandShakingViewModel handShakingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_shaking_main);

        mTabHost = findViewById(R.id.tabhost_fragments);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content_tab);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"),
                ContactsFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("chat").setIndicator("Chat"),
                ChatFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("calls").setIndicator("Calls"),
                CallFragment.class,null);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
