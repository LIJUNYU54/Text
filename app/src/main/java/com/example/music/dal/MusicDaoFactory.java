package com.example.music.dal;

import android.content.Context;

import com.example.music.type.Music;


public class MusicDaoFactory {
    //如果是单例，就用getInstance();
    public static IDao<Music> newInstance(Context context) {
        return new MusicDao();
    }
}
