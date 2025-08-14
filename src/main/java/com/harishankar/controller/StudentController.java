package com.harishankar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.harishankar.model.Student;
import com.harishankar.service.StudentService;
import com.harishankar.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v2/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * Get all students
     */
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        log.info("Fetching all students");
        List<Student> students = studentService.getAllStudents();
        return CommonUtil.success(students,  HttpStatus.OK);
    }

    /**
     * Get student by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id) {
        log.info("Fetching student with ID: {}", id);
        Student student = studentService.getStudentById(id);
        return CommonUtil.success(student, HttpStatus.OK);
    }

    /**
     * Create new student
     */
    @PostMapping
    public ResponseEntity<?> createStudent( @RequestBody Student student) {
        log.info("Creating new student: {}", student);
        Student savedStudent = studentService.saveStudents(student);
        return CommonUtil.success(savedStudent, HttpStatus.CREATED);
    }

    /**
     * Update student
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        log.info("Updating student with ID: {}", id);
        student.setRoll_no(id);
        Student updatedStudent = studentService.updateStudents(student);
        return CommonUtil.success(updatedStudent, HttpStatus.OK);
    }

    /**
     * Delete student
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        log.info("Deleting student with ID: {}", id);
        studentService.deleteStudents(id);
        return CommonUtil.successMessage("Student deleted successfully", HttpStatus.OK);
    }
}
