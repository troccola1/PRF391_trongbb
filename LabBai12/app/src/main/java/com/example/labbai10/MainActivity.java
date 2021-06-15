package com.example.labbai10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button) findViewById(R.id.btnStartService);
        btnStart.setOnClickListener(this);

        Button btnStop = (Button) findViewById(R.id.btnBoundService);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartService:
                startActivity(new Intent(MainActivity.this, StartServiceActivity.class));
                break;
            case R.id.btnBoundService:
                startActivity(new Intent(MainActivity.this, BoundServiceActivity.class));
                break;
        }
    }
}