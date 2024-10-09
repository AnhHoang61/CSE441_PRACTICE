package com.example.sinhvien;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Student student = (Student) getIntent().getSerializableExtra("student");

        TextView studentDetails = findViewById(R.id.studentDetails);

        String details = "ID: " + student.id + "\n" +
                "Name: " + student.fullName.first + " " + student.fullName.midd + " " + student.fullName.last + "\n" +
                "Gender: " + student.gender + "\n" +
                "Birth Date: " + student.birthDate + "\n" +
                "Email: " + student.email + "\n" +
                "Address: " + student.address + "\n" +
                "Major: " + student.major + "\n" +
                "GPA: " + student.gpa + "\n" +
                "Year: " + student.year;
        studentDetails.setText(details);
    }
}
