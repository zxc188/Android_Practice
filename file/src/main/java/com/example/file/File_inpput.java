package com.example.file;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Administrator on 2017/9/6.
 */

public class File_inpput extends AppCompatActivity {
    private Button bt_save;
    private Button bt_read;
    private EditText ed_input;
    private TextView textView_show;
    private static final String password_remember = "PASSWORD_REMEMBER";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_input);
        bt_save = (Button) findViewById(R.id.bt_save);
        bt_read = (Button) findViewById(R.id.bt_read);
        ed_input = (EditText) findViewById(R.id.ed_input);
        textView_show = (TextView) findViewById(R.id.textview_show);
    }

    public void File_input_output(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_save:
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("File_Main");
                    BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);
                    out.write(ed_input.getText().toString().getBytes(StandardCharsets.UTF_8));
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_read:
                StringBuilder stringBuilder = new StringBuilder("");
                try {
                    FileInputStream fileOutputStream = new FileInputStream("File_Main");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fileOutputStream));
                    try {
                        while (reader.ready()) {
                            stringBuilder.append(reader.readLine());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                textView_show.setText(stringBuilder.toString());
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(password_remember, false);
                Intent intent = new Intent();
                intent.setClass(File_inpput.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}
