package com.example.music.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.music.Adapter.likeAdapter;
import com.example.music.R;
import com.example.music.song.song;
import com.example.music.song.song1;
import com.example.music.song.song2;
import com.example.music.type.User;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    ListView wolistview;
    private View arrowRight;
    TextView textView;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_fragment_two,null,false);
        wolistview=view.findViewById(R.id.like_list);
        textView=view.findViewById(R.id.wo_name);
        List<User> users=new ArrayList<>();
        users.add(new User("all around me"));
        users.add(new User("yummy"));

        likeAdapter likeAdapter=new likeAdapter(getContext(),users);
        wolistview.setAdapter(likeAdapter);

        wolistview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        toNewActivity(position);
                    }
                }
        );
        return view;
    }
    private void toNewActivity(int position){
        Intent i;
        switch (position){
            case 0:
                i = new Intent(getContext(), song.class);
                break;
            case 1:
                i = new Intent(getContext(), song1.class);
                break;
            default:
                i = new Intent(getContext(), song2.class);
                break;

        }
        startActivity(i);
    }


}
