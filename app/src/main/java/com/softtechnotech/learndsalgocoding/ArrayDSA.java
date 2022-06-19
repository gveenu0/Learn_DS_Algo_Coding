package com.softtechnotech.learndsalgocoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
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
import java.util.Scanner;

public class ArrayDSA extends AppCompatActivity {

    ProgressDialog nDialog;
    String videoId;
    YouTubePlayerView youTubePlayerView;
    ListView listVideo;
    AdapterList objAdapter;
    FrameLayout framLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_algo_array);
        listVideo = findViewById(R.id.list_video);
        ImageView iv_youtube_thumnail=findViewById(R.id.iv);
        framLayout = findViewById(R.id.youtube_player_view);
        init();

//        TextView tv01 = findViewById(R.id.tv01);

//        getLifecycle().addObserver(youTubePlayerView);


//            Log.e("VideoId is->","" + videoId);
//            String img_url="http://img.youtube.com/vi/"+videoId+"/0.jpg"; // this is link which will give u thumnail image of that video
//            Picasso.with(ArrayDSA.this)
//                    .load(img_url)
//                    .placeholder(R.drawable.java_vector)
//                    .into(iv_youtube_thumnail);

//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        tv01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playYtVideo(videoId, youTubePlayerView);
//            }
//        });
    }


    public String extractYoutubeId(String url){
        String query = null;
        String id = null;
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
    public void playYtVideo(String vId){
//        framLayout.removeAllViews();
        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        framLayout.addView(youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(vId, 0);
            }
        });

    }

    public void startSomethingWentWrongActivity(){
//        youTubePlayerView.release();
        framLayout.removeAllViews();
        Intent intent = new Intent(this, somethingWentWrong.class);
        startActivity(intent);
        nDialog.dismiss();
    }
    public void startHomeActivity(){
//        youTubePlayerView.release();
        framLayout.removeAllViews();
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        startHomeActivity();
    }

    private void init(){
        ArrayList<String>  videoName = getVideoInfo(0);
        ArrayList<String>  videoLink = getVideoInfo(1);
        Log.e("Video_Name", videoName + "");
        Log.e("Video_Link", videoLink + "");
        videoId=extractYoutubeId(videoLink.get(0));
        Log.e("Video_Id", videoId + "");
        objAdapter = new AdapterList(getApplicationContext(), videoName);
        listVideo.setAdapter(objAdapter);
        playYtVideo(videoId);
        listVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Position", i + "");
                Log.e("Position", videoLink.get(i) + "");
                videoId=extractYoutubeId(videoLink.get(i));
                Log.e("Video_Id", videoId + "");
                playYtVideo(videoId);
            }
        });
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
