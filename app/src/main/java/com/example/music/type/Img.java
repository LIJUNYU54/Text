package com.example.music.type;

import java.util.Arrays;

public class Img {
     int [] songimg;
     String singer;


     public Img(int[] songimg, String singer) {
          this.songimg = songimg;
          this.singer = singer;
     }

     public int[] getSongimg() {
          return songimg;
     }

     public void setSongimg(int[] songimg) {
          this.songimg = songimg;
     }

     public String getSinger() {
          return singer;
     }

     public void setSinger(String singer) {
          this.singer = singer;
     }

     @Override
     public String toString() {
          return "Img{" +
                  "songimg=" + Arrays.toString(songimg) +
                  ", singer='" + singer + '\'' +
                  '}';
     }
}
