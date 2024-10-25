package com.example.sinhvien;

import com.google.gson.annotations.SerializedName;

public class Student {
    String id;
    @SerializedName("full_name")
    FullName fullName;
    String gender;
    String birthDate;
    String email;
    String address;
    String major;
    double gpa;
    int year;

    // Constructor cho lớp Student
    public Student(String id, FullName fullName, String gender, String birthDate,
                   String email, String address, String major, double gpa, int year) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        this.major = major;
        this.gpa = gpa;
        this.year = year;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;  // Trả về đối tượng FullName
    }

    // Phương thức getGpa trả về mảng ký tự
    public char[] getGpa() {
        String gpaString = String.valueOf(gpa); // Chuyển đổi điểm GPA thành chuỗi
        return gpaString.toCharArray(); // Chuyển đổi chuỗi thành mảng ký tự
    }

    public FullName getFull_name() {
        return fullName;
    }

    // ... Các phương thức getter khác

    // Lớp FullName bên trong
    public static class FullName {
        String first;  // Tên
        String last;   // Họ
        String midd;   // Tên đệm

        public FullName(String first, String last, String midd) {
            this.first = first;
            this.last = last;
            this.midd = midd;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public String getMidd() {
            return midd;
        }
    }
}
