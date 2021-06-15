package com.example.lab_bai10;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ImplicitIntentActivity extends Activity implements View.OnClickListener {

    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        mSpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.intents, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        Button but = (Button) findViewById(R.id.button1);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = mSpinner.getSelectedItemPosition();
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                break;

            case 1:
                // to search for Funix on goole map
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=funix"));
                break;

            case 2:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
