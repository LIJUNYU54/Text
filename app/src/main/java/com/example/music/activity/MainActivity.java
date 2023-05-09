package com.example.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;
import com.example.music.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    Button supermanger;
    EditText adminname, adminpassword;
    ImageView register, login;
    MyDatabaseHelper myDatabaseHelper;




//    private static final int GO_HOME = 0;//去主页
//    private static final int GO_LOGIN = 1;//去登录页
//
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Usertext text) {
//            switch (text.what) {
//                case GO_HOME://去主页
//                    Intent intent = new Intent(MainActivity.this, login.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//                case GO_LOGIN://去登录页
//                    Intent intent2 = new Intent(MainActivity.this, fristActivity.class);
//                    startActivity(intent2);
//                    finish();
//                    break;
//            }
//        }
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this, com.example.music.activity.login.class);
                startActivity(intent1);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, com.example.music.activity.register.class);
                startActivity(intent);
            }
        });
        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
    }
//
//    private void auto(){
//        if (Usertext.getInstance().hasUserInfo(this))
//        {
//            mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
//        } else {
//            mHandler.sendEmptyMessageAtTime(GO_LOGIN, 2000);
//        }
//
//    }
}