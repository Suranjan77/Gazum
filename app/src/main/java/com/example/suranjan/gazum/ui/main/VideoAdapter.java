package com.example.suranjan.gazum.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suranjan.gazum.R;
import com.example.suranjan.gazum.ui.search.SearchActivity;
import com.example.suranjan.gazum.youtube.retrofitPart.model.Item;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter <VideoAdapter.VideoHolder>{

    private List<Item> searchItem = new ArrayList<>();
    private Context mContext;
    private VideoListFragment.OnFragmentInteractionListener videoPlayer;

    public void setVideoPlayer(VideoListFragment.OnFragmentInteractionListener videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    public void setSearchData(List<Item> searchItem, Context context) {
        this.searchItem = searchItem;
        this.mContext = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoAdapter.VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView itemView = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_video_description, viewGroup, false);
        itemView.setClickable(true);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoHolder videoHolder, int i) {
        final Item currentItem = searchItem.get(i);

        //Glide for youtube video thumbnail image
        Glide.with(mContext)
                .load(currentItem.getSnippet().getThumbnails().getHigh().getUrl())
                .into(videoHolder.thumbnail);

        videoHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer.playVideo(currentItem.getId().getVideoId());
            }
        });

        videoHolder.videoTitle.setText(currentItem.getSnippet().getTitle());
        videoHolder.channelName.setText(currentItem.getSnippet().getChannelTitle());
    }

    @Override
    public int getItemCount() {
        return searchItem.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private TextView videoTitle;
        private TextView channelName;
        private ImageView thumbnail;
        private CardView cardView;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_video_list);
            thumbnail = itemView.findViewById(R.id.view_video_thumbnail);
            videoTitle = itemView.findViewById(R.id.view_video_title);
            channelName = itemView.findViewById(R.id.view_video_channel_name);
        }
    }
}
