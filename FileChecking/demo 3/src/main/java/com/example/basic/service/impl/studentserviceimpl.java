package com.example.basic.service.impl;
import com.example.basic.entity.Student;
import com.example.basic.repository.UserRepository;
import com.example.basic.repository.studentRepository;
import com.example.basic.service.studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class studentserviceimpl implements studentservice {
    private studentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public studentserviceimpl(studentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllstudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    /*@Override
    public User register(User user) {
        return userRepository.save(user);
    }*/

    @Override
    public Student  getStudentbyId(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void DeleteStudent(Long id) {studentRepository.deleteById(id);}

    @Override
    public List<Student> getByKeyword(String keyword) {
        return studentRepository.findByKeyword(keyword);
    }


}

