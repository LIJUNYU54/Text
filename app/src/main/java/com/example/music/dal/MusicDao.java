package com.example.music.dal;

import android.os.Environment;


import com.example.music.type.Music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicDao implements IDao<Music> {

//    String musicDir = "/sdcard/Music";
//    private Object File;

    public List<Music> getData() {
//        File=new File(musicDir);
        List<Music> musics = new ArrayList<>();
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            File =new File(musicDir);
            File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            File[] files = musicDir.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        String fileName = files[i].getName();
                        if (fileName.toLowerCase().endsWith(".mp3")) {
                            Music music = new Music();
                            music.setPath(files[i].getAbsolutePath());
                            music.setTitle(fileName.substring(0, fileName.length() - 4));
                            musics.add(music);
                        }
                    }
                }
            }
        }
        return musics;
    }
}
