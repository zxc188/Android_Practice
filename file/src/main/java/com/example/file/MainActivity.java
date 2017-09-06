package com.example.file;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String user_name = "USER_NAME";
    private static final String user_password = "USER_PASSWORD";
    private static final String password_remember = "PASSWORD_REMEMBER";
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (sharedPreferences.getBoolean(password_remember, false)) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, File_inpput.class);
            startActivity(intent);
            finish();
        } else {
            bt_login = (Button) findViewById(R.id.bt_login);
            sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString(user_name, "2015011216");
            editor.putString(user_password, "00091413");
            editor.putBoolean(password_remember, false);
            editor.apply();
            bt_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
