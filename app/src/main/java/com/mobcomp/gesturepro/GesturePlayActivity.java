package com.mobcomp.gesturepro;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class GesturePlayActivity extends AppCompatActivity {

    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_play);

        progressBar = (ProgressBar) findViewById(R.id.progrss);
        vv = (VideoView) findViewById(R.id.vv);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);

        String url = getIntent().getStringExtra("EXTRA_URL");
        uri = Uri.parse(url);

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(isContinuously){
                    vv.start();
                }
            }
        });

        isContinuously = false;
        progressBar.setVisibility(View.VISIBLE);
        vv.setMediaController(mediacontroller);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
