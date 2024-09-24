package com.example.ex03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edt1,edt2,edt3;
    private Button btncong,btntru,btnnhan,btnchia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt1=findViewById(R.id.edta);
        edt2=findViewById(R.id.edtb);
        edt3=findViewById(R.id.edtc);

        btncong=findViewById(R.id.btncong);
        btntru=findViewById(R.id.btntru);
        btnnhan=findViewById(R.id.btnnhan);
        btnchia=findViewById(R.id.btnchia);

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt("0"+edt1.getText());
                int b=Integer.parseInt("0"+edt2.getText());
                int c=a+b;
                edt3.setText(String.valueOf(c));
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                int c = a - b;
                edt3.setText(String.valueOf(c));
            }
        });
        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                int c = a * b;
                edt3.setText(String.valueOf(c));
            }
        });
        btnchia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                if(b==0) {
                    edt3.setText("B phai khac 0");
                }
                else {
                    int c = a / b;
                    edt3.setText(String.valueOf(c));
                }
            }
        });
    }
}