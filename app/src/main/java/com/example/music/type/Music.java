package com.example.music.type;

public class Music {
    private String title;
    private String path;

    public Music() {
    }

    public Music(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Music [Title=" + title + ",Path=" + path;
    }
}