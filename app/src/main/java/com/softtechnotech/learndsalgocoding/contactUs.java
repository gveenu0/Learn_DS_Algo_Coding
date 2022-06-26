package com.softtechnotech.learndsalgocoding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class contactUs extends AppCompatActivity {
    ImageView facebook, linkedIn, instagram, youTube;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        facebook = findViewById(R.id.facebook);
        linkedIn = findViewById(R.id.linkedIn);
        instagram = findViewById(R.id.instagram);
        youTube = findViewById(R.id.youTube);
        home = findViewById(R.id.home);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFacebookActivity();
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLinkedInActivity();
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInstagramActivity();
            }
        });
        youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startYouTubeActivity();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHomeActivity();
            }
        });
    }
    public void startHomeActivity(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        startActivity(intent);
    }
    public void startFacebookActivity(){
        Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/veenu.g1"));
        startActivity(facebook);
    }
    public void startLinkedInActivity(){
        Intent linkedIn = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/gveenu"));
        startActivity(linkedIn);
    }
    public void startInstagramActivity(){
        Intent instagram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/urbanladkaa/"));
        startActivity(instagram);
    }
    public void startYouTubeActivity(){
        Intent youTube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC7XuwLzxGQb9GukrCVSseTw"));
        startActivity(youTube);
    }
}
