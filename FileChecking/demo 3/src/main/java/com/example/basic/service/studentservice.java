package com.example.basic.service;

import com.example.basic.entity.Student;

import java.util.List;
import java.util.Optional;

public interface studentservice {
    List<Student> getAllstudents();

    Student saveStudent(Student student);

    /*User register(User user);*/


    Student getStudentbyId(Long id);

    Student updateStudent(Student student);

    void DeleteStudent(Long id);

    public List<Student> getByKeyword(String keyword);





}
