package com.example.file;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String user_name = "USER_NAME";
    private static final String user_password = "USER_PASSWORD";
    private static final String password_remember = "PASSWORD_REMEMBER";
    private Button bt_login;
    private CheckBox checkBox_remember;
    private EditText ed_user;
    private EditText ed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_login = (Button) findViewById(R.id.bt_login);
        checkBox_remember = (CheckBox) findViewById(R.id.checkBox_remember);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_password = (EditText) findViewById(R.id.ed_password);
        sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(user_name, "2015011216");
        editor.putString(user_password, "00091413");
        editor.putBoolean(password_remember, false);
        editor.apply();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_user.getText().toString().equals(sharedPreferences.getString(user_name, null)) &&
                        ed_password.getText().toString().equals(sharedPreferences.getString(user_password, null))) {
                    Intent intent = new Intent();
                    intent.setClass(Login.this, File_inpput.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "用户或密码错误", Toast.LENGTH_LONG).show();
                }
            }
        });
        checkBox_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean(password_remember, true);
                    editor.apply();
                } else {
                    editor.putBoolean(password_remember, false);
                    editor.apply();
                }
            }
        });
    }
}
