package com.example.lab_bai7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;

    private Button mBtnChangeImage;

    private ImageView mImageView;

    private int mResId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        mImageView = findViewById(R.id.imgView);

        mBtnChangeImage = findViewById(R.id.btnChangeImage);
        mBtnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mResId) {
                    case 1:
                        mImageView.setImageResource(R.drawable.image1);
                        break;
                    case 2:
                        mImageView.setImageResource(R.drawable.image2);
                        break;
                    case 3:
                        mImageView.setImageResource(R.drawable.image3);
                        break;
                    case 4:
                        mImageView.setImageResource(R.drawable.image4);
                        break;
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton1:
                mResId = 1;
                break;
            case R.id.radioButton2:
                mResId = 2;
                break;
            case R.id.radioButton3:
                mResId = 3;
                break;
            case R.id.radioButton4:
                mResId = 4;
                break;
            default:
                mResId = 1;
                break;
        }

    }
}