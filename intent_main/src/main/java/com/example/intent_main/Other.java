package com.example.intent_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Other extends AppCompatActivity {
    private Button btback;
    private String one;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back);
        Intent intent = getIntent();
        one = intent.getStringExtra("one");
        Toast.makeText(Other.this, one, Toast.LENGTH_LONG).show();
        btback = (Button) findViewById(R.id.back);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback = new Intent();
                goback.putExtra("result", one+"11111111");
                goback.setClass(Other.this, MainActivity.class);
                setResult(2, goback);
                finish();
            }
        });
    }
}
