package com.example.handshaking.Activities.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handshaking.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendRequestActivity extends AppCompatActivity {

    public CardView invite;
    public TextView invitationMessage;
    TextView contactName;
    TextView contactPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        contactName =  findViewById(R.id.fr_contact_name);
        contactPhone =  findViewById(R.id.fr_contact_phone);

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
                Toast.makeText(getApplicationContext(), "Friendship Request Sent", Toast.LENGTH_LONG).show();
                invite.setEnabled(false);
                invite.setVisibility(View.GONE);
            }
        });


    }
}
