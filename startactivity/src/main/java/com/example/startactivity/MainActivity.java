package com.example.startactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static String START_ACTION = "Start_This";
    private Button bt_start;
    private Button bt_call;
    private Button bt_sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_start = (Button) findViewById(R.id.start_by_action);
        bt_call = (Button) findViewById(R.id.bt_call);
        bt_sms = (Button) findViewById(R.id.bt_sms);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(START_ACTION);
                startActivity(intent);
            }
        });
        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+10086));
                startActivity(intent);
            }
        });
        bt_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+10086));
                startActivity(intent);
            }
        });
    }
}
