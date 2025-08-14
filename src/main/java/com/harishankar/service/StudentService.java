package com.harishankar.service;

import java.util.List;

import com.harishankar.model.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();
	public Student saveStudents(Student student);
	public Student getStudentById(Integer id);
	public void deleteStudents(Integer id);
	public Student updateStudents(Student student);

}
