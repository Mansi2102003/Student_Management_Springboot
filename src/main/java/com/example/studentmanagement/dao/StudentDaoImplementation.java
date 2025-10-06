package com.example.studentmanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.model.Student;
import java.util.List;


@Repository
public class StudentDaoImplementation implements StudentDao {
	 @Autowired
     private JdbcTemplate jdbcTemplate;

	@Override
	//It contains code to save the data into database
	//it is repository class which is used to interact with db 
	public int save(Student student) {
		String sql = "INSERT INTO Students(name, course, email, ph_no, address) VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update (sql,
				student.getName(),
				student.getCourse(),
				student.getEmail(),
				student.getPh_no(),
				student.getAddress());
	}
	
	@Override
	public Student findById(int id) {
		String sql = "SELECT * FROM students WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
	}
	
	@Override
	public int update(Student student) {
		String sql = "UPDATE students SET name = ?, course =?, email =?, ph_no =?, address =? WHERE id=?";
		return jdbcTemplate.update(sql, 
				student.getId(),
				student.getName(),
				student.getCourse(), 
				student.getEmail(), 
				student.getPh_no(), 
				student.getAddress()
				);
	}
	
	@Override
	public List<Student> findAll() {
		String sql = "SELECT * from students";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
	}
	
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM students WHERE id = ? ";
		return jdbcTemplate.update(sql,id);
	}

}
