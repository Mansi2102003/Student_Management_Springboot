package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

@RestController // tells springs that it handles HTTP requests and return response
@RequestMapping("/api/students") // sets base url for endpoint inside controller
public class StudentController {
	
  @Autowired 
  private StudentService studentService;
  
  @PostMapping //maps HTTP request to this method
  public ResponseEntity<Student> createStudent(@RequestBody Student student) { //takes jSON body and convert into student object automatically 
	    return ResponseEntity.ok(studentService.addStudent(student)); //calls add student method to add user
	}
}
