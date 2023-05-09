package com.example.music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.music.R;
import com.example.music.type.User;

import java.util.List;

public class likeAdapter extends BaseAdapter {

    Context context;
    List<User> users;

    int[] wo_Tp={R.mipmap.aroun,R.mipmap.aroun,R.mipmap.aroun};

    public likeAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }
    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_like, null, false);
        }

        ImageView tp= convertView.findViewById(R.id.tp3);
        TextView name= convertView.findViewById(R.id.wo_name);
        User user=getItem(position);
        name.setText(user.getName());
        tp.setImageResource(wo_Tp[position]);
        return convertView;
    }
}


