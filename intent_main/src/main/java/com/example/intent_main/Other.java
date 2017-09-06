package com.example.intent_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Other extends AppCompatActivity {
    private Button btback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back);
        btback = (Button) findViewById(R.id.back);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int one = intent.getIntExtra("one", -1);
                int two = intent.getIntExtra("two", -1);
                Toast.makeText(Other.this, ""+one+"+"+two, Toast.LENGTH_LONG).show();
                Intent goback = new Intent();
                intent.putExtra("result", "" + one + two);
                setResult(2, goback);
            }
        });
    }
}
