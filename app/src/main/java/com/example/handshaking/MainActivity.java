package com.example.handshaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.handshaking.IdentificationChecking.IdentityCheckingActivity;

public class MainActivity extends AppCompatActivity {

    CardView mGoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoButton = findViewById(R.id.go_button);
        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }
        });

    }

    private void go(){
        Intent identityChecking = new Intent(this, IdentityCheckingActivity.class);
        startActivity(identityChecking);
    }
}
