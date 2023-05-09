package com.example.music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter<T> extends android.widget.BaseAdapter {
    private Context context;
    private List<T> data;
    private LayoutInflater inflater;

    public BaseAdapter(Context context, List<T> data) {
        setContext(context);
        setData(data);
        setInflater();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("参数Context不允许为null");
        }
        this.context = context;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater() {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
}