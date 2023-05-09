package com.example.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;
import com.example.music.database.MyDatabaseHelper;
import com.example.music.type.Usertext;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    EditText loginname,loginpassword;
    LinearLayout loginbutton;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginname=findViewById(R.id.loginname);
        loginpassword=findViewById(R.id.loginpassword);
        loginbutton=findViewById(R.id.loginbutton);
//
//        EventBus.getDefault().register(this);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=loginname.getText().toString().trim();
                String password=loginpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<Usertext> data = myDatabaseHelper.getAllDATA();
                    boolean userdata = false;//和数据库里的值相对应
                    for (int i = 0; i < data.size(); i++) {
                        Usertext usertext = data.get(i);    //可存储账号数量
                        if (name.equals(usertext.getName()) && password.equals(usertext.getPassword())) {
                            userdata = true;//userdata为true登录成功
                            break;
                        } else {
                            userdata = false;
                        }
                    }
                    if (userdata) {//判断userdata的结果
                        Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, fristActivity.class);
                        String getname=loginname.getText().toString();
                        intent.putExtra("username",getname);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(login.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }

        });
        myDatabaseHelper=new MyDatabaseHelper(login.this);
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        String name=loginname.getText().toString().trim();
//        save(name);
//    }
//
//    private void save(String name) {
//        FileOutputStream out = null;
//        BufferedWriter writer = null;
//        try {
//            out = openFileOutput("name_data", Context.MODE_PRIVATE);
//            writer = new BufferedWriter(new OutputStreamWriter(out));
//            writer.write(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (writer != null) {
//                    writer.close();
//                } } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}