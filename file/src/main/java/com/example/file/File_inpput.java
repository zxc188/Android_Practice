package com.example.file;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    FileOutputStream fileOutputStream = openFileOutput("File_Main.txt",MODE_PRIVATE);
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
                    FileInputStream fileInputStream = openFileInput("File_Main.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
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
                Toast.makeText(File_inpput.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                Log.v("asdnf", "asjdif");
                SharedPreferences sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(password_remember, false);
                Intent intent = new Intent();
                intent.setClass(File_inpput.this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}
