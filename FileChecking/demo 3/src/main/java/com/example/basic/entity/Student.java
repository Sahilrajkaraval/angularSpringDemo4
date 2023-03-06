package com.example.basic.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SahilStudent")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "studentName")
    private String studentName;
    @Column(name = "schoolName")
    private String schoolName;
    @Column(name = "className")
    private String className;

    public Student(){

    }

    public Student( String studentName, String schoolName, String className) {
        super();
        this.studentName = studentName;
        this.schoolName = schoolName;
        this.className = className;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}



