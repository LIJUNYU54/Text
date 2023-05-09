package com.example.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.music.R;
import com.example.music.fragment.home;
import com.example.music.fragment.musicspace;
import com.example.music.fragment.search;


import java.util.ArrayList;
import java.util.List;

public class fristActivity extends AppCompatActivity  implements View.OnClickListener {
    EditText loginname;
    TextView logintext;
    LinearLayout home_layout,search_layout,music_layout;

    home homefragment= new home();
    musicspace musicspacefragment= new musicspace();
    search searchfragment= new search();
    List<Fragment> fragments =new ArrayList<>();
    int currPosititon=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);

//        loginname=findViewById(R.id.loginname);

        logintext=findViewById(R.id.logintext);
        home_layout=findViewById(R.id.home_layout);
        music_layout=findViewById(R.id.music_layout);
        search_layout=findViewById(R.id.search_layout);

        home_layout.setOnClickListener(this);
        search_layout.setOnClickListener(this);
        music_layout.setOnClickListener(this);

        fragments.add(homefragment);
        fragments.add(searchfragment);
        fragments.add(musicspacefragment);


        changeFragment(0);

//        String name=loginname.getText().toString().trim();
//        String getname = load();
        Intent intent=getIntent();
        String getname=intent.getStringExtra("username");
        if(!TextUtils.isEmpty(getname)){
            logintext.setText(getname);
//            logintext.setText(getname);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_layout:
                changeFragment(0);
                currPosititon=0;
                break;
            case R.id.search_layout:
                changeFragment(1);
                currPosititon=1;
                break;
            case  R.id.music_layout:
                changeFragment(2);
                currPosititon=2;
                break;
        }
    }
    public void changeFragment(int position){
        if(currPosititon==position){
            return;
        }
        FragmentManager fm = getSupportFragmentManager () ;
        FragmentTransaction bt = fm.beginTransaction();
        bt.replace(R.id.fragment,fragments.get(position));
        bt.commit();
    }
//    private String load() {
//        FileInputStream in = null;
//        BufferedReader reader = null;
//        StringBuilder content = new StringBuilder();
//        try {
//            in = openFileInput("name_data");
//            reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                content.append(line);
//            } } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return content.toString();
//    }

}
