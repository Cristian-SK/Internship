package com.example.testproject;

import static com.example.testproject.fragment.TvChannelFragment.EXTRA_LINK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class TvActivity extends AppCompatActivity {


    ExoPlayer player;
//    String videoURL = "https://nbculocallive.akamaized.net/hls/live/2037499/puertorico/stream1/master.m3u8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tv);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_LINK);

        StyledPlayerView playerView = findViewById(R.id.playerView);
        player = new ExoPlayer.Builder(TvActivity.this).build();

                        playerView.setPlayer(player);
                        MediaItem mediaItem = MediaItem.fromUri(url);
                        player.setMediaItem(mediaItem);
                        player.prepare();
                        player.setPlayWhenReady(true);
    }


    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
        player.release();
        releasePlayer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
        player.release();
        releasePlayer();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void releasePlayer() {
        player.release();
    }
}