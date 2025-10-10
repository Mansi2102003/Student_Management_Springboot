package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.model.Student;

import jakarta.transaction.Transactional;

@Service
//It contains core business logic is written
public class StudentServiceImplementation implements StudentService {

	// Autowired annotation means it tells spring to wired studenDao bean
	// automatically
	@Autowired
	private StudentDao studentDao;

	@Override
	// Take Student object as input
	public Student addStudent(Student student) {
		// Call studentsdao's method to save into database
		studentDao.save(student);
		return student;
	}

	@Override
	public Student getStudentById(int id) {
		try {
			return studentDao.findById(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Student with id " + id + " not found");
		}
	}

	@Override
	@Transactional
	public Student updateStudent(Student student) {
		int updated = studentDao.update(student);
		if (updated == 0) {
			throw new ResourceNotFoundException("Student with id " + student.getId() + " not found");
		}
		return student;
	}

	@Override
	public void deleteStudent(int id) {
		int deleted = studentDao.delete(id);
		if (deleted == 0) {
			throw new ResourceNotFoundException("Student with id " + id + " not found");
		}
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.findAll();
	}

	@Override
	public Student getStudentByName(String name) {
		try {
			return studentDao.findByName(name);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Student with Name " + name + " not found");
		}
	}
	
	@Override
	public int updateStudentField(int id, String fieldName, Object value) {
		return studentDao.updateStudentField(id, fieldName, value);
	}

}
