#Student Management System

- A full-stack student management student using spring Boot(java) for backend and HTML, CSS, JS, BOOTSTRAP for frontend.
- It allows user to add, view, update, delete and search a student records stored in PostgreSQL database.

#Features

- Add new student records
- Edit and update existing student information
- Delete student records
- Search student by ID
- View all students in a tabular format
- Responsive UI built with Bootstrap 5

Tech Stack

1.Frontend:
	- HTML5, CSS3, JavaScript (ES6)
	- Bootstrap 5

2.Backend:
	- Java 21
	- Spring Boot 
	- Spring Web (REST API)
	- Spring JDBC / JdbcTemplate

3.Database:
	- PostgreSQL

4.Tools:
	- Eclipse IDE
	- Apache Tomcat (embedded in Spring Boot)
	- Maven for dependency management
	
#Project Structure
	
Student_Management_Springboot/
│
├── src/main/java/com/example/studentmanagement/
│   ├── controller/
│   │   └── StudentController.java
│   ├── dao/
│   │   ├── StudentDao.java
│   │   └── StudentDaoImplementation.java
│   ├── model/
│   │   └── Student.java
│   ├── service/
│   │   └── StudentService.java
│   |	└── StudentServiceImplementation.java
│   ├── exception/
│   |	└── ResourceNotFoundException.java 
│   │   └── GlobalExceptionHandler.java
│   └── StudentmanagementApplication.java
│
├── src/main/resources/
│   ├── application.properties
│   └── static/
│       ├── index.html
│       └── js/
│           └── app.js
│
└── pom.xml
	
	