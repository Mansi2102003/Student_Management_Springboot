
package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;

import com.example.studentmanagement.service.StudentServiceImplementation;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentServiceImplementation studentService;

	@Test
	void testViewAllStudents() throws Exception {
		List<Student> students = List.of(new Student(1, "Mansi", "CS", "mansi@gmail.com", 9978908765l, "Pune"),
				new Student(2, "Ravi", "IT", "ravi@gmail.com", 8345687988l, "Mumbai"));

		when(studentService.getAllStudents()).thenReturn(students);

		mockMvc.perform(get("/api/students")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].name").value("Mansi"));
	}

	@Test
	void testDeleteStudent() throws Exception {
		doNothing().when(studentService).deleteStudent(1);

		mockMvc.perform(get("/api/students/1")).andExpect(status().isOk());

	}

}