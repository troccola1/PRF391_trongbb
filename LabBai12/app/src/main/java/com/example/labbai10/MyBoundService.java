package com.example.labbai10;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Calendar;
import java.util.Date;

public class MyBoundService extends Service {

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
