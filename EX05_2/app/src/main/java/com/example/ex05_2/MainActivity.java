package com.example.ex05_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtA, edtB, edtC;
    private Button btnContinue, btnGiaiPT, btnThoat;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        btnContinue = findViewById(R.id.btnContinue);
        btnGiaiPT = findViewById(R.id.btnGiaiPT);
        btnThoat = findViewById(R.id.btnThoat);
        tvResult = findViewById(R.id.tvResult);

        // Thiết lập sự kiện cho nút "Giải PT"
        btnGiaiPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aStr = edtA.getText().toString();
                String bStr = edtB.getText().toString();
                String cStr = edtC.getText().toString();

                if (aStr.isEmpty() || bStr.isEmpty() || cStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập a, b, c", Toast.LENGTH_SHORT).show();
                    return;
                }

                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                double c = Double.parseDouble(cStr);

                solveQuadraticEquation(a, b, c);
            }
        });

        // Thiết lập sự kiện cho nút "Thoát"
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Phương thức giải phương trình bậc 2
    private void solveQuadraticEquation(double a, double b, double c) {
        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            tvResult.setText("Pt có 2 No: x1=" + x1 + ", x2=" + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            tvResult.setText("Pt có 1 No: x=" + x);
        } else {
            tvResult.setText("Pt vô nghiệm.");
        }
    }
}
