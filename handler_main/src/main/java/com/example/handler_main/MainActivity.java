package com.example.handler_main;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaplayer;
    private SeekBar seekbar_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaplayer = new MediaPlayer();
        seekbar_music = (SeekBar) findViewById(R.id.seekbar_music);
        try {
            mediaplayer.setDataSource(MainActivity.this, Uri.parse("http://123.207.149.200/music/ykzzldx.mp3"));
            mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaplayer.start();
                    seekbar_music.setMax(mediaplayer.getDuration());
                }
            });
            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    try {
                        mp.setDataSource(MainActivity.this, Uri.parse("http://123.207.149.200/music/ykzzldx.mp3"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                seekbar_music.setProgress(msg.arg1);
            }
        };
        Thread thread = new Thread() {
            @Override
            public void run() {
                Message message = new Message();
                while (mediaplayer.isPlaying()) {
                    message.arg1 = mediaplayer.getCurrentPosition();
                    handler.sendMessage(message);
                }
            }
        };
    }
}
