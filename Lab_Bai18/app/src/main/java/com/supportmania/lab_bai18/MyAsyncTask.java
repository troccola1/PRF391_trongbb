package com.supportmania.lab_bai18;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    private Activity mContenxt;

    public MyAsyncTask(Activity contextParent) {
        this.mContenxt = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mContenxt, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        ProgressBar progressBar = (ProgressBar) mContenxt.findViewById(R.id.prbDemo);

        int number = values[0];

        progressBar.setProgress(number);

        TextView textView = (TextView) mContenxt.findViewById(R.id.txtStatus);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mContenxt, "Olie, Finished", Toast.LENGTH_SHORT).show();
    }
}
