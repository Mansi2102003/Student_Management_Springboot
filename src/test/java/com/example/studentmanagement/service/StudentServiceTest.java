package com.example.studentmanagement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentServiceImplementation;

@SpringBootTest
public class StudentServiceTest {

	 @Mock
	 private StudentDao studentDao;

	     @InjectMocks // Mocks the dependency of StudentController
	     private StudentServiceImplementation studentService;
	     

	     @Test
	     void addStudentTest() throws Exception {
	        Student student = new Student(1,"Mansiii","JAVA", "mansu@gmail.com", "9988562374","Ahmedabad");
	        when(studentDao.save(student)).thenReturn(1);
	        
	        Student saved = studentService.addStudent(student);
	         assertNotNull(saved);
	         assertEquals("Mansiii", saved.getName());
	     }
	 }
