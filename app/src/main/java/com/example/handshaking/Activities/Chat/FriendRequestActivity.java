package com.example.handshaking.Activities.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handshaking.Entity.FriendshipRequest;
import com.example.handshaking.Entity.Contact;
import com.example.handshaking.R;
import com.example.handshaking.Repository.FriendRepository;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendRequestActivity extends AppCompatActivity {

    public CardView invite;
    public TextView invitationMessage;
    ProgressBar progressBar;
    TextView contactName;
    TextView contactPhone;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        progressBar = findViewById(R.id.progressBar);
        contactName =  findViewById(R.id.fr_contact_name);
        contactPhone =  findViewById(R.id.fr_contact_phone);
        progressBar.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();
        String contactInformation = bundle.getString("contact_json_data");

        try {
            JSONObject contactInformationJSON = new JSONObject(contactInformation);

            contactName.setText(contactInformationJSON.getString("contact_name"));
            contactPhone.setText(contactInformationJSON.getString("contact_phone"));

        }catch (JSONException e) {
            e.printStackTrace();
        }


        invite = findViewById(R.id.invite_button);

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FriendRepository friendRepository = new FriendRepository();
                String name = "Ayrton Sousa Marinho";
                String telephone = "+5588996692296";

                Contact user = new Contact();
                user.setName(name);
                user.setTelephone(telephone);

                Contact friend = new Contact();
                friend.setName(contactName.getText().toString());
                friend.setTelephone(contactPhone.getText().toString());

                FriendshipRequest friendshipRequest = new FriendshipRequest();
                friendshipRequest.setOriginContact(user);
                friendshipRequest.setFriendContact(friend);

                progressBar.setVisibility(View.VISIBLE);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean loading = true;

                        while(loading){
                            message = friendRepository.sendFriendshipRequest(friendshipRequest);
                            loading = false;
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                invite.setEnabled(false);
                                invite.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                            }
                        });
                    }
                });
                thread.start();

//




            }
        });


    }
}
