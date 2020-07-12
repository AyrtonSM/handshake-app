package com.example.handshaking.Activities.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handshaking.R;

public class FriendRequestActivity extends AppCompatActivity {

    public Button invite;
    public TextView invitationMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        invite = findViewById(R.id.invite_button);

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Friendship Request Sent", Toast.LENGTH_LONG).show();
                invite.setEnabled(false);
                invite.setVisibility(View.GONE);
            }
        });


    }
}
