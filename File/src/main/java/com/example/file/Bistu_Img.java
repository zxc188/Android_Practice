package com.example.file;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class Bistu_Img extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bistu_img);
        sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
        final Handler hadler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.arg1 == 1) {
                    Intent intent = new Intent();
                    intent.setClass(Bistu_Img.this, File_inpput.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(Bistu_Img.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Thread thread=new Thread(){
            @Override
            public void run() {
                Message message = new Message();
                if (sharedPreferences.getBoolean("PASSWORD_REMEMBER", false)) {
                    message.arg1 = 1;
                } else {
                    message.arg1 = 0;
                }
                hadler.sendMessage(message);
            }
        };
        thread.start();
    }
}
