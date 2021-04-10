package com.example.lab_bai10;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class IntentFilterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_intent_filer);
        Button b1 = (Button) findViewById(R.id.botton1);
        b1.setOnClickListener((view) -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 0397572092"));
            startActivity(intent);
        });
    }
}
