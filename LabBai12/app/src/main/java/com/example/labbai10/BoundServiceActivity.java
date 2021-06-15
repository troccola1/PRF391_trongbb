package com.example.labbai10;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;


import java.util.Date;

public class BoundServiceActivity extends Activity {

    private MyBoundService myBoundService;

    private boolean mIsBound = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        Button btnShowCurrentDate = (Button) findViewById(R.id.btnShowCurrentDate);
        btnShowCurrentDate.setOnClickListener((view) -> {
            if (mIsBound) {
                Date date = myBoundService.getCurrentDate();
                Toast.makeText(BoundServiceActivity.this,
                        String.valueOf(date), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIsBound) {
            unbindService(connection);
            mIsBound = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            myBoundService = binder.getService();
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName argO) { mIsBound = false; }
    };
}
