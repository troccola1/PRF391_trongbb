package com.example.project3_alarmclock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

import java.security.Provider;
import java.util.Objects;

public class AlarmService extends Service {

    private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String on_off = Objects.requireNonNull(intent.getExtras()).getString("ON_OFF");
        assert on_off != null;
        switch (on_off) {
            case Constants.ADD_INTENT:
                Uri uri = Settings.System.DEFAULT_ALARM_ALERT_URI;
                mMediaPlayer = MediaPlayer.create(this, uri);
                mMediaPlayer = MediaPlayer.create(this, uri);
                mMediaPlayer.start();
                break;
            case Constants.OFF_INTENT:
                int alarmId = intent.getExtras().getInt("AlarmId");
                if (mMediaPlayer != null && mMediaPlayer.isPlaying() && alarmId == AlarmReceiver.sPendingId) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }
                break;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.reset();
    }
}
