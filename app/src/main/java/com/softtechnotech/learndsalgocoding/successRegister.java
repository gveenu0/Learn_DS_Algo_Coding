package com.softtechnotech.learndsalgocoding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class successRegister extends AppCompatActivity {
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityLogin();
            }
        });
    }
    public void startActivityLogin(){
        Intent intent = new Intent(this, com.softtechnotech.learndsalgocoding.MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(successRegister.this, "Access denied", Toast.LENGTH_SHORT).show();
    }
}
