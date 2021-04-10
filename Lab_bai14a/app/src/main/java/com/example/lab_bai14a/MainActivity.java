package com.example.lab_bai14a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnShowAll = (Button) findViewById(R.id.btnShowAllContact);
        btnShowAll.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, ShowAllContactActivity.class);
            startActivity(intent);
        });
    }
}