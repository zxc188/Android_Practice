package com.example.file;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Bistu_Img extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bistu_img);
        sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("PASSWORD_REMEMBER", false)) {
            Intent intent = new Intent();
            intent.setClass(Bistu_Img.this, File_inpput.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.setClass(Bistu_Img.this, Login.class);
            startActivity(intent);
        }
    }
}
