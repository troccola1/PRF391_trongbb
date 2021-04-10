package com.example.prm391x_02_vn_project1_trongbbfx02929funixeduvn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // Khai báo
    private RadioButton mquiz1_a, mquiz1_b, mquiz1_c, mquiz4_a, mquiz4_b, mquiz5_a, mquiz5_b, mquiz5_c, mquiz5_d, mquiz7_a, mquiz7_b, mquiz7_c, mquiz9_a, mquiz9_b, mquiz9_c, mquiz9_d, mquiz10_a, mquiz10_b, mquiz10_c, mquiz10_d;
    private Button mtest;
    private CheckBox mquiz2_a, mquiz2_b, mquiz2_c, mquiz2_d, mquiz8_a, mquiz8_b, mquiz8_c, mquiz8_d;
    private EditText mquiz3, mquiz6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtest = findViewById(R.id.test);
        mtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lưu lại số câu trả lời đúng
                int i = 0;
                // câu hỏi 1
                // findViewById để truy suất vào control theo đúng id tương ứng truyền vào trong hàm
                mquiz1_a = findViewById(R.id.quiz1_a);
                mquiz1_b = findViewById(R.id.quiz1_b);
                mquiz1_c = findViewById(R.id.quiz1_c);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                // isChecked kiểm tra trạng thái là checked (true) hay unchecked (false)
                if (mquiz1_b.isChecked()) {
                    i++;
                }

                // câu hỏi 2
                mquiz2_a = findViewById(R.id.quiz2_a);
                mquiz2_b = findViewById(R.id.quiz2_b);
                mquiz2_c = findViewById(R.id.quiz2_c);
                mquiz2_d = findViewById(R.id.quiz2_d);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz2_a.isChecked() && mquiz2_b.isChecked() && mquiz2_c.isChecked() && !mquiz2_d.isChecked()) {
                    i++;
                }

                // câu hỏi 3
                mquiz3 = findViewById(R.id.quiz3);
                String kq_c3 = "Hà Nội";
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if(mquiz3.length() == kq_c3.length()) {
                    i++;
                }

                // câu hỏi 4
                mquiz4_a = findViewById(R.id.quiz4_a);
                mquiz4_b = findViewById(R.id.quiz4_b);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz4_a.isChecked()) {
                    i++;
                }

                // Câu hỏi 5
                mquiz5_a = findViewById(R.id.quiz5_a);
                mquiz5_b = findViewById(R.id.quiz5_b);
                mquiz5_c = findViewById(R.id.quiz5_c);
                mquiz5_d = findViewById(R.id.quiz5_d);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz5_b.isChecked()) {
                    i++;
                }

                // câu hỏi 6
                mquiz6 = findViewById(R.id.quiz6);
                String kq_c6 = "Ethanol";
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if(mquiz6.length() == kq_c6.length()) {
                    i++;
                }

                // câu hỏi số 7
                mquiz7_a = findViewById(R.id.quiz7_a);
                mquiz7_b = findViewById(R.id.quiz7_a);
                mquiz7_c = findViewById(R.id.quiz7_a);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz7_a.isChecked()) {
                    i++;
                }

                // câu hỏi số 8
                mquiz8_a = findViewById(R.id.quiz8_a);
                mquiz8_b = findViewById(R.id.quiz8_b);
                mquiz8_c = findViewById(R.id.quiz8_c);
                mquiz8_d = findViewById(R.id.quiz8_d);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz8_a.isChecked() && mquiz8_b.isChecked() && !mquiz8_c.isChecked() && mquiz8_d.isChecked()) {
                    i++;
                }

                // câu hỏi 19
                mquiz9_a = findViewById(R.id.quiz9_a);
                mquiz9_b = findViewById(R.id.quiz9_b);
                mquiz9_c = findViewById(R.id.quiz9_c);
                mquiz9_d = findViewById(R.id.quiz9_c);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz9_c.isChecked()) {
                    i++;
                }

                // câu hỏi 10
                mquiz10_a = findViewById(R.id.quiz10_a);
                mquiz10_b = findViewById(R.id.quiz10_b);
                mquiz10_c = findViewById(R.id.quiz10_c);
                mquiz10_d = findViewById(R.id.quiz10_d);
                // Kiểm tra câu trả lời có đúng ko(nếu đúng thì tăng thêm 1 đơn vị)
                if (mquiz10_c.isChecked()) {
                    i++;
                }

                Toast.makeText(view.getContext(), "Bạn đã đúng " + i + " câu ^.^", Toast.LENGTH_SHORT).show();
            }
        });
    }
}