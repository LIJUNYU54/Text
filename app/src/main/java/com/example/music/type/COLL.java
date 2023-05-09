package com.example.music.type;

import android.graphics.Bitmap;

public class COLL {
    int id;
   String Coll;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColl() {
        return Coll;
    }

    public void setColl(String coll) {
        Coll = coll;
    }

    public COLL(int id, String coll) {
        this.id = id;
        Coll = coll;
    }

    @Override
    public String toString() {
        return "COLL{" +
                "id=" + id +
                ", Coll='" + Coll + '\'' +
                '}';
    }
}
