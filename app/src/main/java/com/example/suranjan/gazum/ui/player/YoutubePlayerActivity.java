package com.example.suranjan.gazum.ui.player;

import android.content.Intent;
import android.os.Bundle;

import com.example.suranjan.gazum.R;
import com.example.suranjan.gazum.utilities.YoutubeApiKey;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youtubePlayerView;
    private String videoId;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube_player);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            videoId = intent.getStringExtra("searchQuery");
        }


        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.view_video_player);

        youtubePlayerView.initialize(YoutubeApiKey.KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(videoId);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
