package com.example.sinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextGPA;
    Button buttonSave;
    Student student;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextGPA = findViewById(R.id.editTextGPA);
        buttonSave = findViewById(R.id.buttonSave);

        // Nhận dữ liệu sinh viên từ Intent
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");
        if (student != null) {
            editTextFirstName.setText(student.fullName.first);
            editTextLastName.setText(student.fullName.last);
            editTextGPA.setText(String.valueOf(student.gpa));
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudentInfo();
            }
        });
    }

    private void saveStudentInfo() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        double gpa;

        try {
            gpa = Double.parseDouble(editTextGPA.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid GPA", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật thông tin sinh viên
        student.fullName.first = firstName;
        student.fullName.last = lastName;
        student.gpa = gpa;

        // Gửi lại thông tin đã cập nhật cho MainActivity
        Intent intent = new Intent();
        intent.putExtra("updated_student", (CharSequence) student);
        setResult(RESULT_OK, intent);
        finish();
    }
}
