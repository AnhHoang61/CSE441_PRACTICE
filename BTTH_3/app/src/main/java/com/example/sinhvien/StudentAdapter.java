package com.example.sinhvien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }

    public StudentAdapter(List<Student> studentList, OnItemClickListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student, listener);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void updateList(List<Student> newList) {
        studentList = newList;
        notifyDataSetChanged();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName, textViewGpa;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.studentID);
            textViewName = itemView.findViewById(R.id.studentName);
            textViewGpa = itemView.findViewById(R.id.studentGPA);
        }

        public void bind(final Student student, final OnItemClickListener listener) {
            textViewId.setText(student.getId());
            // Sử dụng phương thức getFullName() để lấy họ và tên
            textViewName.setText(student.getFullName().getLast() + " " + student.getFullName().getFirst());
            textViewGpa.setText(String.valueOf(student.getGpa()));

            itemView.setOnClickListener(v -> listener.onItemClick(student));
        }
    }
}
