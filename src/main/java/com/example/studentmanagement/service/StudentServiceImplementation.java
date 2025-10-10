package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.model.Student;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImplementation implements StudentService {
	
	//Autowired annotation means it tells spring to wired studenDao bean automatically
	@Autowired
	private StudentDao studentDao;
	
	//It contains core business logic is written
	@Override
	public Student addStudent(Student student) {  //Take Student object as input
		studentDao.save(student);   //Call studentsdao's method to save into database
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
	            throw new ResourceNotFoundException("Student with id " + name + " not found");
	        }
		}

}

	

