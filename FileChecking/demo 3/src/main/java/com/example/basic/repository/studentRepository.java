package com.example.basic.repository;

import com.example.basic.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface studentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM new_students s WHERE s.firstname like %:keyword% or s.lastname like %:keyword%", nativeQuery = true)
    List<Student> findByKeyword(@Param("keyword") String keyword);


}
