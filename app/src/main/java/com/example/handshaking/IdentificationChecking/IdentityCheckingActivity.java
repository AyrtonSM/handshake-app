package com.example.handshaking.IdentificationChecking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.handshaking.HandShakingMainActivity;
import com.example.handshaking.R;

public class IdentityCheckingActivity extends AppCompatActivity {

    Button mVerifyButton;
    EditText mVerificationNumber;
    EditText mVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_checking);

        mVerifyButton = findViewById(R.id.verify_button);
        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPhoneNumber();
            }
        });
    }

    private void verifyPhoneNumber(){
        mVerificationNumber = findViewById(R.id.verification_number);
        String phoneNumber = mVerificationNumber.getText().toString();
        // SEND SMS SERVICE
        // ASSYNCRONOUS TASK

        if(success()){
            startActivity(new Intent(this, HandShakingMainActivity.class));
        }


    }

    private boolean success(){
        return true;
    }
}
