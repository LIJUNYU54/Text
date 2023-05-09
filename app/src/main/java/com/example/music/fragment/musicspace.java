package com.example.music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.music.R;


import java.util.ArrayList;
import java.util.List;

public class musicspace extends Fragment  implements  View.OnClickListener {

    like likefragment=new like();
    singer singerfragment=new singer();
    List<Fragment> fragments=new ArrayList<>();
    LinearLayout like,singer;
    int currPosititon=-1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.musicspace,container,false);
        like=view.findViewById(R.id.like);
        singer=view.findViewById(R.id.singer);

        like.setOnClickListener(this);
        singer.setOnClickListener(this);

        fragments.add(likefragment);
        fragments.add(singerfragment);

        changeFragment(0);
        currPosititon=0;
        return view;
    }
//    public  void  onStart() {
//        super.onStart();
//        FragmentManager fragmentManager = getChildFragmentManager();
//    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.like:
                changeFragment(0);
                currPosititon=0;
                break;
            case R.id.singer:
                changeFragment(1);
                currPosititon=1;
                break;
        }
    }

    private  void changeFragment(int position){
        if(currPosititon==position){
            return;
        }
        FragmentManager fm = getChildFragmentManager () ;
        FragmentTransaction bt = fm.beginTransaction();
        bt.replace(R.id.fragmentx,fragments.get(position));
        bt.commit();

    }



}
