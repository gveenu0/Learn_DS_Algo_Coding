package com.softtechnotech.learndsalgocoding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HeapDSA extends AppCompatActivity {

    String videoId;
    YouTubePlayerView youTubePlayerView;
    ListView listVideo;
    AdapterList objAdapter;
    Button fullscreen;
    YouTubePlayerTracker tracker;
    ArrayList<String>  videoName;
    ArrayList<String>  videoLink;
    int curVideoIndex = 0;
    Intent intent;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tracker = new YouTubePlayerTracker();
        
        setContentView(R.layout.activity_ds_algo_detail);
        listVideo = findViewById(R.id.list_video);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        fullscreen = findViewById(R.id.button_fullscreen);
        videoName = getVideoInfo(0);
        videoLink = getVideoInfo(1);
        videoId=extractYoutubeId(videoLink.get(0));
        intent = new Intent(this, ytPlayAct.class);
        handler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        handler.postDelayed(() -> findViewById(R.id.loadingPanel).setVisibility(View.GONE), 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        youTubePlayerView.removeYouTubePlayerListener(new AbstractYouTubePlayerListener(){
        });
    }

    public String extractYoutubeId(String url){
        return url.split("v=")[1].trim();
    }

    private void init(){
        Log.e("Video_Link", videoLink + "");
        objAdapter = new AdapterList(getApplicationContext(), videoName);
        listVideo.setAdapter(objAdapter);
        HeapDSA.this.getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(tracker);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        extractYoutubeId(videoLink.get(0)),
                        0f
                );
                listVideo.setOnItemClickListener((adapterView, view, i, l) -> {
                    curVideoIndex = i;
                    videoId=extractYoutubeId(videoLink.get(i));
                    Log.e("Video_Id", videoId + "");
                    YouTubePlayerUtils.loadOrCueVideo(
                            youTubePlayer, HeapDSA.this.getLifecycle(),
                            videoId, 0f
                    );
                });
                setPlaybackSpeedButtonsClickListeners(youTubePlayer);
                handler.postDelayed(() -> setFullscreenButtonListener(), 5000);
            }

        });
    }
    private void setFullscreenButtonListener(){
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        fullscreen.setOnClickListener(view -> startYtFull());
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
            InputStream XmlFileInputStream = getResources().openRawResource(R.raw.heap);
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
    public void startYtFull(){
        intent.putExtra("videoId",videoId);
        intent.putExtra("className","HeapDSA.java");
        intent.putExtra("timeStamp", tracker.getCurrentSecond());
        startActivity(intent);
    }
}
