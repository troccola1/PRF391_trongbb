package com.example.lab_bai16;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button mBtnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    private GPSTrackerService mGPSGpsTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {mPermission},
                        REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBtnShowLocation = (Button) findViewById(R.id.btnGetLocation);

       mBtnShowLocation.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View argO) {
               mGPSGpsTracker = new GPSTrackerService(MainActivity.this);

               if (mGPSGpsTracker.canGetLocation()) {
                   double latitude = mGPSGpsTracker.getLatitude();
                   double longitude = mGPSGpsTracker.getLongitude();

                   Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                   + latitude +"\nLong: " + longitude, Toast.LENGTH_LONG).show();
               } else {
                   mGPSGpsTracker.showSettingsAlert();
               }
           }
       });
    }
}