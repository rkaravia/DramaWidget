package io.github.rzoller.dramaticlockscreen;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class DramaService extends Service {
    private static String TAG = "DramaService";

    private AtomicBoolean isRunning = new AtomicBoolean(false);

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isRunning.compareAndSet(false, true)) {
            final AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            final int prevVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
            int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.drama);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    audio.setStreamVolume(AudioManager.STREAM_MUSIC, prevVolume, 0);
                    isRunning.set(false);
                    stopSelf();
                }
            });
            mp.start();
        }

        return START_NOT_STICKY;
    }
}
