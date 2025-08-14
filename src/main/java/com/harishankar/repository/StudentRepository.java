package com.harishankar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harishankar.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
