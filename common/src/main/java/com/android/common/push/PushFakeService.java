package com.android.common.push;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.android.common.manager.UserManager;

/**
 * Created by kiddo on 17-7-11.
 */

public class PushFakeService extends Service {
    public static final int NOTIFICATION_ID = 1001;

    public static void startForeground(Service service) {
        service.startService(new Intent(service, PushFakeService.class));
        service.startForeground(NOTIFICATION_ID, new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, new Notification());
        String userName = UserManager.getInstance(this).getUserName();
        if (userName == null){
            userName = "kiddo";
        }
        InitPush.getInstance().bindUser(userName);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
