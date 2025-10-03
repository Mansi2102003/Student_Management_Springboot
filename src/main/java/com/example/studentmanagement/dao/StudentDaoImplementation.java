package com.example.studentmanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.model.Student;

@Repository
public class StudentDaoImplementation implements StudentDao {
	 @Autowired
     private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Student student) {
		String sql = "INSERT INTO Students(id, name, course, email, ph_no, address) VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update (sql,
				student.getId(),
				student.getName(),
				student.getCourse(),
				student.getEmail(),
				student.getPhno(),
				student.getAddress());
	}

}
