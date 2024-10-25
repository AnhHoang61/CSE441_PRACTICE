package com.example.sinhvien;

import static com.example.sinhvien.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load data từ file JSON
        loadStudentData();

        adapter = new StudentAdapter(studentList, student -> {
            // Chuyển sang màn hình chi tiết
            Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
            intent.putExtra("student", (CharSequence) student); // Đảm bảo truyền đúng kiểu dữ liệu
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAddStudent = findViewById(R.id.fab_add_student);
        fabAddStudent.setOnClickListener(v -> {
            // Xử lý thêm sinh viên mới
            Toast.makeText(this, "Thêm sinh viên mới", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadStudentData() {
        try {
            InputStream inputStream = getAssets().open("students.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String jsonString = jsonBuilder.toString();
            Gson gson = new Gson();
            Student[] students = gson.fromJson(jsonString, Student[].class);
            studentList = new ArrayList<>(Arrays.asList(students));
        } catch (IOException e) {
            e.printStackTrace();
            studentList = new ArrayList<>(); // Khởi tạo danh sách rỗng nếu có lỗi
        }
    }

    // Nạp menu vào taskbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Tìm kiếm
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Xử lý sự kiện tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý tìm kiếm khi nhấn Enter
                searchStudents(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý tìm kiếm khi nhập văn bản
                searchStudents(newText);
                return false;
            }
        });

        return true;
    }

    // Xử lý khi chọn item trong menu
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                // Sắp xếp danh sách sinh viên từ A-Z
                sortStudentListAZ();
                return true;

            case R.id.action_option_1:
                // Xử lý tùy chọn 1 nếu cần
                return true;

            case R.id.action_option_2:
                // Xử lý tùy chọn 2 nếu cần
                return true;

            case R.id.action_option_3:
                // Xử lý tùy chọn 3 nếu cần
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Hàm tìm kiếm sinh viên
    private void searchStudents(String query) {
        List<Student> filteredList = new ArrayList<>();
        for (Student student : studentList) {
            String fullName = student.getFullName().getLast() + " " + student.getFullName().getFirst();
            if (fullName.toLowerCase().contains(query.toLowerCase()) ||
                    student.getId().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(student);
            }
        }
        adapter.updateList(filteredList);
    }

    // Hàm sắp xếp danh sách từ A-Z
    private void sortStudentListAZ() {
        Collections.sort(studentList, (s1, s2) ->
                s1.getFullName().getLast().compareToIgnoreCase(s2.getFullName().getLast())
        );
        adapter.notifyDataSetChanged(); // Thông báo cho adapter biết rằng danh sách đã thay đổi
    }
}
