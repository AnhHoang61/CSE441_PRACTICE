package com.example.ex07_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    EditText edtAA, edtBB;
    Button btnsendtong, btnsendhieu;
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);

        edtAA = findViewById(R.id.edtAA);
        edtBB = findViewById(R.id.edtBB);
        btnsendtong = findViewById(R.id.btnsendtong);
        btnsendhieu = findViewById(R.id.btnsendhieu);

        // Nhận intent
        myintent = getIntent();
        // Lấy dữ liệu từ Intent
        int a = myintent.getIntExtra("soa", 0);
        int b = myintent.getIntExtra("sob", 0);

        // Chuyển đổi giá trị nguyên thành chuỗi để gán cho EditText
        edtAA.setText(String.valueOf(a));
        edtBB.setText(String.valueOf(b));

        btnsendtong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = a + b;
                Intent intent = new Intent();
                intent.putExtra("kq", sum);
                setResult(33, intent);
                finish();
            }
        });

        btnsendhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sub;
                if (a >= b) {
                    sub = a - b;  // Nếu a lớn hơn hoặc bằng b, thì lấy a trừ b
                } else {
                    sub = b - a;  // Nếu b lớn hơn a, thì lấy b trừ a
                }

                Intent intent = new Intent();
                intent.putExtra("kq", sub);
                setResult(34, intent);
                finish();
            }
        });
    }
}
