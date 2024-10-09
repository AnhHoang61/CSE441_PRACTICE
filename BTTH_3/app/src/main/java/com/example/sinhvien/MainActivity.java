package com.example.sinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tải dữ liệu từ JSON
        studentList = loadStudentsFromJson();
        studentAdapter = new StudentAdapter(studentList, this);
        recyclerView.setAdapter(studentAdapter);
    }

    // Tạo menu (tìm kiếm, sắp xếp)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Tìm kiếm sinh viên
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Student");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        return true;
    }

    // Xử lý khi người dùng chọn item menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        try {
            switch (id) {
                case R.id.action_edit:
                    // Mở Activity để chỉnh sửa thông tin sinh viên
                    Intent editIntent = new Intent(MainActivity.this, EditStudentActivity.class);
                    startActivity(editIntent);
                    return true;

                case R.id.action_sort_id_asc:
                    // Sắp xếp theo ID tăng dần
                    sortStudentsByIdAsc();
                    return true;

                case R.id.action_sort_id_desc:
                    // Sắp xếp theo ID giảm dần
                    sortStudentsByIdDesc();
                    return true;

                case R.id.action_sort_gpa_asc:
                    // Sắp xếp theo GPA tăng dần
                    sortStudentsByGpaAsc();
                    return true;

                case R.id.action_sort_gpa_desc:
                    // Sắp xếp theo GPA giảm dần
                    sortStudentsByGpaDesc();
                    return true;

                // Các mã khác...
                default:
                    return super.onOptionsItemSelected(item);
            }
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();
            Toast.makeText(this, "Đã xảy ra lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }



    // Lọc danh sách sinh viên theo tên
    private void filter(String text) {
        List<Student> filteredList = new ArrayList<>();
        for (Student student : studentList) {
            if (student.fullName.first.toLowerCase().contains(text.toLowerCase()) ||
                    student.fullName.last.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(student);
            }
        }
        studentAdapter.filterList(filteredList);
    }

    // Sắp xếp danh sách sinh viên theo tên từ A-Z
    private void sortStudents() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.fullName.first.compareToIgnoreCase(s2.fullName.first);
            }
        });
        studentAdapter.notifyDataSetChanged();
    }

    private void sortStudentsByIdAsc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.id.compareTo(s2.id);
            }
        });
        studentAdapter.notifyDataSetChanged();
    }

    private void sortStudentsByIdDesc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s2.id.compareTo(s1.id);
            }
        });
        studentAdapter.notifyDataSetChanged();
    }

    private void sortStudentsByGpaAsc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.gpa, s2.gpa);
            }
        });
        studentAdapter.notifyDataSetChanged();
    }

    private void sortStudentsByGpaDesc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.gpa, s1.gpa);
            }
        });
        studentAdapter.notifyDataSetChanged();
    }


    // Tải dữ liệu sinh viên từ file JSON
    private List<Student> loadStudentsFromJson() {
        List<Student> students = new ArrayList<>();
        try {
            InputStream is = getAssets().open("students.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                JSONObject fullNameObject = jsonObject.getJSONObject("full_name");
                Student.FullName fullName = new Student.FullName(
                        fullNameObject.getString("first"),
                        fullNameObject.getString("last"),
                        fullNameObject.getString("midd"));

                String gender = jsonObject.getString("gender");
                String birthDate = jsonObject.getString("birth_date");
                String email = jsonObject.getString("email");
                String address = jsonObject.getString("address");
                String major = jsonObject.getString("major");
                double gpa = jsonObject.getDouble("gpa");
                int year = jsonObject.getInt("year");

                students.add(new Student(id, fullName, gender, birthDate, email, address, major, gpa, year));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return students;
    }
}
