package com.harishankar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harishankar.model.Student;
import com.harishankar.repository.StudentRepository;
import com.harishankar.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudents(Student student) {
        log.info("Saving student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudents(Student student) {
        log.info("Updating student with Roll No: {}", student.getRoll_no());
        return studentRepository.findById(student.getRoll_no())
                .map(existing -> studentRepository.save(student))
                .orElseThrow(() -> new RuntimeException("Student not found with Roll No: " + student.getRoll_no()));
    }

    @Override
    public void deleteStudents(Integer id) {
        log.info("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(Integer id) {
        log.info("Fetching student by ID: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }
}
