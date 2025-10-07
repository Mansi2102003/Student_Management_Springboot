const API_URL = 'http://localhost:8080/api/students'; 

document.addEventListener('DOMContentLoaded', () => {
    fetchStudents();
});

const studentForm = document.getElementById('student-form');
const studentTableBody = document.getElementById('student-table-body');
const formTitle = document.getElementById('form-title');
const submitBtn = document.getElementById('submit-btn');

let currentStudentId = null;

// Fetch all students and render them
async function fetchStudents() {
    const response = await fetch(API_URL);
    const students = await response.json();
    renderStudents(students);
}

// Render student data in the table
function renderStudents(students) {
    studentTableBody.innerHTML = '';
    students.forEach(student => {
        const row = studentTableBody.insertRow();
        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.course}</td>
            <td>${student.email}</td>
            <td>${student.ph_no}</td>
            <td>${student.address}</td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="editStudent(${student.id})">Edit</button>
                <button class="btn btn-danger btn-sm" onclick="deleteStudent(${student.id})">Delete</button>
            </td>
        `;
    });
}

// Handle form submission for creating or updating students
studentForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const studentData = {
        name: document.getElementById('name').value,
        course: document.getElementById('course').value,
        email: document.getElementById('email').value,
        ph_no: document.getElementById('ph_no').value,
        address: document.getElementById('address').value
    };

    if (currentStudentId) {
        // Update an existing student
        await fetch(`${API_URL}/${currentStudentId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(studentData)
        });
    } else {
        // Create a new student
        await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(studentData)
        });
    }

    resetForm();
    fetchStudents();
});

// Populate the form for editing
async function editStudent(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const student = await response.json();

    document.getElementById('name').value = student.name;
    document.getElementById('course').value = student.course;
    document.getElementById('email').value = student.email;
    document.getElementById('ph_no').value = student.ph_no;
    document.getElementById('address').value = student.address;
    
    currentStudentId = student.id;
    formTitle.innerText = 'Edit Student';
    submitBtn.innerText = 'Update Student';
}

// Delete a student
async function deleteStudent(id) {
    if (confirm('Are you sure you want to delete this student?')) {
        await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        fetchStudents();
    }
}

// Reset the form after submission or cancellation
function resetForm() {
    studentForm.reset();
    currentStudentId = null;
    formTitle.innerText = 'Add New Student';
    submitBtn.innerText = 'Add Student';
}

// Add event listener for the search form
const findStudentForm = document.getElementById('find-student-form');
const searchResultDiv = document.getElementById('search-result');

findStudentForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const studentId = document.getElementById('find-student-id').value;
    searchResultDiv.innerHTML = ''; // Clear previous result

    if (studentId) {
        try {
            const response = await fetch(`${API_URL}/${studentId}`);
            if (response.ok) {
                const student = await response.json();
                displayFoundStudent(student);
            } else {
                searchResultDiv.innerHTML = `<div class="alert alert-warning">No student found with ID: ${studentId}</div>`;
            }
        } catch (error) {
            console.error('Error fetching student:', error);
            searchResultDiv.innerHTML = `<div class="alert alert-danger">An error occurred while fetching the student.</div>`;
        }
    }
});

// Display the found student's data
function displayFoundStudent(student) {
    if (student) {
        searchResultDiv.innerHTML = `
            <div class="card p-3 bg-light">
                <h5>Student Details</h5>
                <p><strong>ID:</strong> ${student.id}</p>
                <p><strong>Name:</strong> ${student.name}</p>
                <p><strong>Course:</strong> ${student.course}</p>
                <p><strong>Email:</strong> ${student.email}</p>
                <p><strong>Phone No:</strong> ${student.ph_no}</p>
                <p><strong>Address:</strong> ${student.address}</p>
            </div>
        `;
    } else {
        searchResultDiv.innerHTML = `<div class="alert alert-warning">No student found.</div>`;
    }
}

