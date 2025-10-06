package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  
  
  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable int id) {
      return ResponseEntity.ok(studentService.getStudentById(id));
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) throws Exception{
      student.setId(id);
      return ResponseEntity.ok(studentService.updateStudentInfo(student));
  }
  
  @GetMapping
  public ResponseEntity<List<Student>> findAllStudent(){
	  return ResponseEntity.ok(studentService.getAllStudents());
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable int id){
	  studentService.deleteStudent(id);
	  return ResponseEntity.noContent().build();
  }
  
}
