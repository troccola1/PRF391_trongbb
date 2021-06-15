package com.example.lab_bai9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSubtracion, mMultiplication, mDivision;

    private EditText mInputA, mInputB;

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDivision = (Button) findViewById(R.id.division);
        mSubtracion = (Button) findViewById(R.id.subtraction);
        mMultiplication = (Button) findViewById(R.id.multiplication);
        mInputA = (EditText) findViewById(R.id.inputA);
        mInputB = (EditText) findViewById(R.id.inputB);
        mResult = (TextView) findViewById(R.id.result);

        // Listener in varialbe
        mMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.parseDouble(
                        mInputA.getText().toString()) * Double.parseDouble(
                        mInputB.getText().toString());
                mResult.setText("Result" + mInputA.getText().toString() + "*" + mInputB.getText().toString() + "=" + result);
            }
        });

        // Inline anonymous listener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mDivision) {
                    double result = Double.parseDouble(mInputA.getText().toString())
                            / Double.parseDouble(mInputB.getText().toString());
                    mResult.setText("Kết quả: " + mInputA.getText().toString() + "/"
                    + mInputB.getText().toString() + "=" + result);
                }
            }
        };
        mDivision.setOnClickListener(listener);
        mSubtracion.setOnClickListener(this);
        }
    //onClick in XML
    public void addition(View v) {
        double result = Double.parseDouble(mInputA.getText().toString())
                + Double.parseDouble(mInputB.getText().toString());
        mResult.setText("Kết quả: " + mInputA.getText().toString() + "+"
        + mInputB.getText().toString() + "=" + result);
    }

    //Activity is listener
    @Override
    public void onClick(View v) {
        if (v == mSubtracion) {
            double result = Double.parseDouble(mInputA.getText().toString())
                    - Double.parseDouble(mInputB.getText().toString());
            mResult.setText("Result: " + mInputA.getText().toString() + "-"
            + mInputB.getText().toString() + "=" + result);
        }
    }


}