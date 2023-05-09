package com.example.music.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.music.R;
import com.example.music.activity.About;
import com.example.music.activity.Download;
import com.example.music.activity.History;
import com.example.music.item_view;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class search extends Fragment implements View.OnClickListener{

    private ImageView blurImageView;
    private ImageView avatarImageView;
    private TextView username;
    item_view collect,about,history,download;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);

        blurImageView = view.findViewById(R.id.iv_blur);
        username = view.findViewById(R.id.user_name);
//        collect=view.findViewById(R.id.collect);
        about=view.findViewById(R.id.about);
        download=view.findViewById(R.id.download);
        history=view.findViewById(R.id.history);

        download.setOnClickListener(this);

        Intent intent = getActivity().getIntent();
        String getname = intent.getStringExtra("username");
        if (!TextUtils.isEmpty(getname)) {
            username.setText(getname);
        }
            return view;
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.download:
                Intent intent=new Intent(getContext(), Download.class);
                startActivity(intent);
                break;
            case R.id.history:
                Intent intent1=new Intent(getContext(), History.class);
                startActivity(intent1);
                break;
            case R.id.about:
                Intent intent2=new Intent(getContext(), About.class);
                startActivity(intent2);
                break;


        }
    }
}

