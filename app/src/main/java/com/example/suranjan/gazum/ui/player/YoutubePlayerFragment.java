package com.example.suranjan.gazum.ui.player;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suranjan.gazum.R;
import com.example.suranjan.gazum.utilities.YoutubeApiKey;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerFragment extends Fragment {

    private static final String VIDEO_ID = "param1";

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private String videoId;


    public YoutubePlayerFragment() {
        // Required empty public constructor
    }

    public static YoutubePlayerFragment newInstance(String param1) {
        YoutubePlayerFragment fragment = new YoutubePlayerFragment();
        Bundle args = new Bundle();
        args.putString(VIDEO_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            videoId = getArguments().getString(VIDEO_ID);
        }

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        youTubePlayerView = getView().findViewById(R.id.view_video_player);
        youTubePlayerView.initialize(YoutubeApiKey.KEY, onInitializedListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_youtube_player, container, false);
    }
}
