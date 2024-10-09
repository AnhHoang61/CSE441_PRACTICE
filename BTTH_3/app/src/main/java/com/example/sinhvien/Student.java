package com.example.sinhvien;

public class Student {
    String id;
    FullName fullName;
    String gender;
    String birthDate;
    String email;
    String address;
    String major;
    double gpa;
    int year;

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

    public static class FullName {
        String first;
        String last;
        String midd;

        public FullName(String first, String last, String midd) {
            this.first = first;
            this.last = last;
            this.midd = midd;
        }
    }
}
