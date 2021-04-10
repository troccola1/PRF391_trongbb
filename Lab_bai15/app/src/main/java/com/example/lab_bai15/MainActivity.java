package com.example.lab_bai15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvName = (TextView) findViewById(R.id.tvName);
        Button btnGetName = (Button) findViewById(R.id.btnGetName);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdateName);
        final MyDbHelper mDB = new MyDbHelper(getApplicationContext());

        Student student = new Student(1, "Bui Binh Trong", "Hoa Binh", "0397572092");
        mDB.addStudent(student);

        btnGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mDB.getStudent(1).getName();
                tvName.setText(name);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student1 = mDB.getStudent(1);
                student.setName("Bui Van A");
                mDB.updateStudent(student);
0
                String name = mDB.getStudent(1).getName();
                tvName.setText(name);
            }
        });
    }
}