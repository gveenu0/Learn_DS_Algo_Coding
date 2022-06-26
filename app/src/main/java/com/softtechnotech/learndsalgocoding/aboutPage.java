package com.softtechnotech.learndsalgocoding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class aboutPage extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        home = findViewById(R.id.home);
        home.setOnClickListener(v -> startHomeActivity());
    }
    public void startHomeActivity(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        startActivity(intent);
    }
}
