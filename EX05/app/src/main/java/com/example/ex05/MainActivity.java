package com.example.ex05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtDuongLich;
    private Button btnChuyenDoi;
    private TextView tvAmLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        EdgeToEdge.enable( this );
        setContentView( R.layout.activity_main );

        edtDuongLich = findViewById( R.id.edtDuongLich );
        btnChuyenDoi = findViewById( R.id.btnChuyenDoi );
        tvAmLich = findViewById( R.id.tvAmLich );

        // Thiết lập sự kiện nhấn nút "Chuyển đổi"
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duongLichString = edtDuongLich.getText().toString();
                if (duongLichString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập năm dương lịch", Toast.LENGTH_SHORT).show();
                    return;
                }

                int duongLich = Integer.parseInt(duongLichString);
                String amLich = convertToAmLich(duongLich);

                // Hiển thị kết quả
                tvAmLich.setText(amLich);
            }
        });
    }

    // Phương thức chuyển đổi năm dương lịch sang năm âm lịch
    private String convertToAmLich(int duongLich) {
        String[] can = {"Canh", "Tân", "Nhâm", "Quý","Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        String[] chi = {"Thân", "Dậu", "Tuất", "Hợi","Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi"};

        int canIndex = duongLich  % 10;
        int chiIndex = duongLich  % 12;

        return can[canIndex] + " " + chi[chiIndex];

    }
}