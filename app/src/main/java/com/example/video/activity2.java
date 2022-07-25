package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class activity2 extends AppCompatActivity {

    VideoView v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        v2=(VideoView)findViewById(R.id.videoView2);
        v2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nature));
        v2.requestFocus();
        v2.start();

    }
}