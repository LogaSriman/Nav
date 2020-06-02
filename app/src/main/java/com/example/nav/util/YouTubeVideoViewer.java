package com.example.nav.util;

public class YouTubeVideoViewer {
    String videoUrl;

    public YouTubeVideoViewer() {
    }

    public YouTubeVideoViewer(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}