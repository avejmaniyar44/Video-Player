package com.example.video;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    TextView name,subs;
    VideoView vw;
    ArrayList <Integer> videolist= new ArrayList<>();
    int currvideo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(TextView)findViewById(R.id.name);
        vw=(VideoView)findViewById(R.id.videoView);
        subs = findViewById(R.id.textView5);

        vw.setMediaController(new MediaController(this));
        vw.setOnCompletionListener(this);

        videolist.add(R.raw.ad);
        videolist.add(R.raw.video);
        setVideo(videolist.get(0));

        subs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String var = subs.getText().toString();
                if (var == "SUBSCRIBE") {
                    subs.setText("SUBSCRIBED");
                    subs.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_notifications_active_24, 0);
                }
                else {
                    subs.setText("SUBSCRIBE");
                    subs.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_notifications_24, 0);

                }
            }
        });

    }


    private void setVideo(Integer id) {
        String uriPath="android.resource://"+getPackageName()+"/"+id;
        Uri uri=Uri.parse(uriPath);
        vw.setVideoURI(uri);
        vw.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        AlertDialog.Builder obj= new AlertDialog.Builder(this);
        obj.setTitle("Playback Finished.!");
        obj.setIcon(R.drawable.ic_baseline_motion_photos_on_24);
        MyListener m= new MyListener();
        obj.setPositiveButton("Replay",m);
        obj.setNegativeButton("Next",m);
        obj.setMessage("Want To replay or play next video?");
        obj.show();
    }

    public void new_video(View view) {
        Intent intent=new Intent(getApplicationContext(),activity2.class);
        startActivity(intent);
    }

    public class MyListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which==-1){
                vw.seekTo(0);
                vw.start();
            }
            else{
                ++currvideo;
                if (currvideo == videolist.size())
                    currvideo=0;
                setVideo(videolist.get(currvideo));
                name.setText("Background Video For Your Videos | Lightning Background Video");
            }

        }
    }

}