package com.example.music.app;

import android.app.Application;


import com.example.music.dal.MusicDaoFactory;
import com.example.music.type.Music;

import java.util.List;

public class MusicPlayerApplication extends Application {
    private List<Music> musics;

    @Override
    public void onCreate() {
        super.onCreate();
        musics = MusicDaoFactory.newInstance(this).getData();
    }

    public List<Music> getMusics() {
        return musics;
    }
}