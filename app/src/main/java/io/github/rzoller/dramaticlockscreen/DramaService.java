package io.github.rzoller.dramaticlockscreen;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class DramaService extends Service {
    private static String TAG = "DramaService";

    public DramaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "onStartCommand");
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.drama);
        mp.start();
        return START_NOT_STICKY;
    }
}
