package com.example.project3_alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;

import java.util.Objects;

public class AlarmReceiver  extends BroadcastReceiver {

    public static int sPendingId;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Intent intentToService = new Intent(context, AlarmService.class);
            try {
                String intentType = Objects.requireNonNull(intent.getExtras()).getString("intentType");
                assert intentType != null;
                switch (intentType) {
                    case Constants.ADD_INTENT:

                        sPendingId = intent.getExtras().getInt("PendingId");
                        intentToService.putExtra("ON_OFF", Constants.ADD_INTENT);
                        context.startService(intentToService);
                        break;
                    case Constants.OFF_INTENT:

                        int alarmId = intent.getExtras().getInt("AlarmId");
                        intentToService.putExtra("ON_OFF", Constants.OFF_INTENT);
                        intentToService.putExtra("AlarmId", alarmId);
                        context.startService(intentToService);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
