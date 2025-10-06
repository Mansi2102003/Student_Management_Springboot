package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.Student;
import java.util.List;


//StudentDao interface created 
//It is a repository class which used to interact with database

public interface StudentDao {
	int save(Student student);
	Student findById(int id);
	int updateStudent(Student student);
	List<Student> findAll();
	int delete(int id);

}
