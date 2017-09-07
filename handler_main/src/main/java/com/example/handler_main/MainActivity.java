package com.example.handler_main;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaplayer;
    private SeekBar seekbar_music;
    private Button bt_start;
    private Button bt_stop;
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaplayer = new MediaPlayer();
        try {
            mediaplayer.setDataSource(MainActivity.this, Uri.parse("http://www.zxcstudio.cn/music/ykzzldx.mp3"));
            mediaplayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        seekbar_music = (SeekBar) findViewById(R.id.seekbar_music);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mediaplayer.start();
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaplayer.isPlaying()) {
                    mediaplayer.stop();
                    bt_start.setVisibility(View.GONE);
                }
            }
        });
        mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar_music.setMax(mediaplayer.getDuration());
                mediaplayer.start();
                thread.start();
            }
        });
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                seekbar_music.setProgress(msg.arg1);
            }
        };
        thread = new Thread() {
            @Override
            public void run() {
                int i=0;
                while (i <= mediaplayer.getDuration()) {
                    Message message = new Message();
                    message.arg1 = i;
                    handler.sendMessage(message);
                    i = mediaplayer.getCurrentPosition();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        thread = null;
        mediaplayer.release();
    }
}
