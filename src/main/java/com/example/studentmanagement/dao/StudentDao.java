package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.Student;


//StudentDao interface created 
//It is a repository class which used to interact with database

public interface StudentDao {
	int save(Student student);
}
