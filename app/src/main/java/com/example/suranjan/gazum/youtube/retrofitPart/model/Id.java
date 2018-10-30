package com.example.suranjan.gazum.youtube.retrofitPart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("videoId")
    @Expose
    private String videoId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Id() {
    }

    /**
     *
     * @param videoId
     * @param kind
     */
    public Id(String kind, String videoId) {
        super();
        this.kind = kind;
        this.videoId = videoId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "Id{" +
                "kind='" + kind + '\'' +
                ", videoId='" + videoId + '\'' +
                '}';
    }
}