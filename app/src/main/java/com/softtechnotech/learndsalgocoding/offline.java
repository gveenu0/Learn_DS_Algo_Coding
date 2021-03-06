package com.softtechnotech.learndsalgocoding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class offline extends AppCompatActivity {
    Button tryAgain, signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        tryAgain = findViewById(R.id.tryAgain);
        signup = findViewById(R.id.signup);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTryAgainActivity();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignupActivity();
            }
        });
    }
    public void startTryAgainActivity(){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
    public void startSignupActivity(){
        Intent intent = new Intent(this, registerPage.class);
        startActivity(intent);
    }
}
