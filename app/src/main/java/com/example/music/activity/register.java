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

public class register extends AppCompatActivity {

    EditText registername,registerpassword;
    LinearLayout registerbtn;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registername=findViewById(R.id.registername);
        registerpassword=findViewById(R.id.registerpassword);
        registerbtn=findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = registername.getText().toString().trim();
                String password = registerpassword.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    myDatabaseHelper.useradd(name,password);
                    Intent intent1 = new Intent(register.this, login.class);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(register.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();}
            }
        });
        myDatabaseHelper = new MyDatabaseHelper(register.this);
    }
}