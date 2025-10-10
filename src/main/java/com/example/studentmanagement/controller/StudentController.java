package com.example.studentmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

//tells springs that it handles HTTP requests and return response
@RestController

//sets base url for endpoint inside controller
@RequestMapping("/api/students")

@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// maps HTTP request to this method
	
	// takes jSON body and convert into student object automatically
	//public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
		// calls add student method to add user
	//	return ResponseEntity.ok(studentService.addStudent(student));
	//}
	@PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest studentReq, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        Student student = new Student();
        student.setName(studentReq.getName());
        student.setCourse(studentReq.getCourse());
        student.setEmail(studentReq.getEmail());
        student.setPh_no(studentReq.getPh_no());
        student.setAddress(studentReq.getAddress());

        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(student);
    }

	

	// Get student by name
	@GetMapping("/name/{name}")
	public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
		Student student = studentService.getStudentByName(name);
		return ResponseEntity.ok(student);
	}

	@GetMapping
	public ResponseEntity<List<Student>> findAllStudent() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) throws Exception {
		student.setId(id);
		return ResponseEntity.ok(studentService.updateStudent(student));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
	// Update specific field by ID
	@PatchMapping("/{id}/update")
	public ResponseEntity<?> updateStudentField(@PathVariable int id, @RequestBody Map<String, Object> updates) {

		try {
			if (updates.isEmpty()) {
				return ResponseEntity.badRequest().body(Map.of("error", "No field provided for update"));
			}

			// Get the field name and its new value
			String fieldName = updates.keySet().iterator().next();
			Object value = updates.get(fieldName);

			int rowsAffected = studentService.updateStudentField(id, fieldName, value);

			if (rowsAffected > 0) {
				return ResponseEntity.ok(Map.of("message", "✅ " + fieldName + " updated successfully"));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("error", "Student not found or update failed"));
			}

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Map.of("error", "Something went wrong"));
		}
	}

}
