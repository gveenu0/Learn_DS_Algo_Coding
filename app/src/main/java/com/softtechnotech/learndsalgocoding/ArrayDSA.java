package com.softtechnotech.learndsalgocoding;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.softtechnotech.learndsalgocoding.utils.FullScreenHelper;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ArrayDSA extends AppCompatActivity {


    ProgressDialog nDialog;
    String videoId;
    YouTubePlayerView youTubePlayerView;
    ListView listVideo;
    AdapterList objAdapter;
    ArrayList<String>  videoName;
    ArrayList<String>  videoLink;
    int curVideoIndex = 0;
    private FullScreenHelper fullScreenHelper = new FullScreenHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_ds_algo_array);
        listVideo = findViewById(R.id.list_video);
        ImageView iv_youtube_thumnail=findViewById(R.id.iv);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        init();

//            String img_url="http://img.youtube.com/vi/"+videoId+"/0.jpg"; // this is link which will give u thumnail image of that video
//            Picasso.with(ArrayDSA.this)
//                    .load(img_url)
//                    .placeholder(R.drawable.java_vector)
//                    .into(iv_youtube_thumnail);

//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }

    }


    public String extractYoutubeId(String url){
        String query, id = null;
        try {
            query = new URL(url).getQuery();
        String[] param = query.split("&");
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
            else{
                id = param1[0];
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Raw File entry wrong", Toast.LENGTH_LONG);
        }
        return id;
    }


    @Override
    public void onBackPressed() {
        if (youTubePlayerView.isFullScreen())
            youTubePlayerView.exitFullScreen();
        else
            super.onBackPressed();
    }

//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            youTubePlayerView.enterFullScreen();
//        }
//        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            youTubePlayerView.exitFullScreen();
//        }
//    }

    private void init(){
        videoName = getVideoInfo(0);
        videoLink = getVideoInfo(1);
        videoId=extractYoutubeId(videoLink.get(0));
        Log.e("Video_Name", videoName + "");
        Log.e("Video_Link", videoLink + "");
        Log.e("Video_Id", videoId + "");
        objAdapter = new AdapterList(getApplicationContext(), videoName);
        listVideo.setAdapter(objAdapter);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        extractYoutubeId(videoLink.get(0)),
                        0f
                );
                listVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        curVideoIndex = i;
                        Log.e("Position", i + "");
                        Log.e("Position", videoLink.get(i) + "");
                        videoId=extractYoutubeId(videoLink.get(i));
                        Log.e("Video_Id", videoId + "");
                        YouTubePlayerUtils.loadOrCueVideo(
                                youTubePlayer, ArrayDSA.this.getLifecycle(),
                                videoId, 0f
                        );
                    }
                });
                setPlaybackSpeedButtonsClickListeners(youTubePlayer);
                setFullscreenButtonListener();
            }

//            @Override
//            public void onPlaybackRateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackRate playbackRate) {
//                TextView playbackSpeedTextView = findViewById(R.id.playback_speed_text_view);
//                String playbackSpeed = "Playback speed: ";
//                playbackSpeedTextView.setText(playbackSpeed + playbackRate);
//            }
        });

    }
    private void setFullscreenButtonListener(){
        Button fullscreen = findViewById(R.id.button_fullscreen);
        fullscreen.setOnClickListener(view -> youTubePlayerView.enterFullScreen());
    }
    private void setPlaybackSpeedButtonsClickListeners(YouTubePlayer youTubePlayer) {
        Button playbackSpeed_50= findViewById(R.id.button_speed_50);
        Button playbackSpeed_100 = findViewById(R.id.button_speed_100);
        Button playbackSpeed_150 = findViewById(R.id.button_speed_150);
        Button playbackSpeed_200 = findViewById(R.id.button_speed_200);

        playbackSpeed_50.setOnClickListener(view -> youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_0_5));
        playbackSpeed_100.setOnClickListener(view -> youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_1));
        playbackSpeed_150.setOnClickListener(view -> youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_1_5));
        playbackSpeed_200.setOnClickListener(view -> youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_2));
    }

    public ArrayList<String> getVideoInfo(int i) {
        ArrayList<String> videoInfo = new ArrayList<>();
        try {
            InputStream XmlFileInputStream = getResources().openRawResource(R.raw.array_file1);
            BufferedReader br = new BufferedReader(new InputStreamReader(XmlFileInputStream));
            String line;
            while ((line = br.readLine()) != null) {
                videoInfo.add(line.split("===")[i].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoInfo;
    }

}
