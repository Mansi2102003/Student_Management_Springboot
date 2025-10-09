const API_URL = "http://localhost:8080/api/students"; // adjust if backend URL differs

// Utility: Get current page name
function currentPage() {
  return window.location.pathname.split("/").pop();
}

// PAGE: ADD STUDENT 
if (currentPage() === "add-student.html") {
  const studentForm = document.getElementById("student-form");

  studentForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const student = {
      name: document.getElementById("name").value,
      course: document.getElementById("course").value,
      email: document.getElementById("email").value,
      ph_no: document.getElementById("ph_no").value,
      address: document.getElementById("address").value,
    };

    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student),
      });

      if (response.ok) {
        alert("✅ Student added successfully!");
        studentForm.reset();
      } else {
        alert("❌ Failed to add student");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("⚠️ Error while adding student");
    }
  });
}

//PAGE: VIEW STUDENTS 
if (currentPage() === "view-students.html") {
  async function fetchStudents() {
    try {
      const response = await fetch(API_URL);
      const students = await response.json();
      const tableBody = document.getElementById("student-table-body");
      tableBody.innerHTML = "";

      students.forEach((student) => {
        const row = `
          <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.course}</td>
            <td>${student.email}</td>
            <td>${student.ph_no}</td>
            <td>${student.address}</td>
            <td>
              <button class="btn btn-danger btn-sm" onclick="deleteStudent(${student.id})">Delete</button>
            </td>
          </tr>`;
        tableBody.insertAdjacentHTML("beforeend", row);
      });
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  }

  async function deleteStudent(id) {
    if (!confirm("Are you sure you want to delete this student?")) return;
    try {
      const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
      if (response.ok) {
        alert("🗑️ Student deleted!");
        fetchStudents();
      } else {
        alert("❌ Failed to delete student");
      }
    } catch (error) {
      console.error("Error deleting student:", error);
    }
  }

  // Make deleteStudent global for inline HTML buttons
  window.deleteStudent = deleteStudent;

  // Fetch when page loads
  fetchStudents();
}

//PAGE: UPDATE STUDENT
if (currentPage() === "update-student.html") {
  const updateForm = document.getElementById("update-student-form");

  updateForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("update-id").value;
    const student = {
      name: document.getElementById("update-name").value,
      course: document.getElementById("update-course").value,
      email: document.getElementById("update-email").value,
      ph_no: document.getElementById("update-ph_no").value,
      address: document.getElementById("update-address").value,
    };

    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student),
      });

      if (response.ok) {
        alert("✅ Student updated successfully!");
        updateForm.reset();
      } else {
        alert("❌ Failed to update student");
      }
    } catch (error) {
      console.error("Error updating student:", error);
    }
  });
}

//PAGE: SEARCH STUDENT
if (currentPage() === "search-student.html") {
  const findForm = document.getElementById("find-student-form");
  const resultDiv = document.getElementById("search-result");

  findForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("find-student-id").value;

    try {
      const response = await fetch(`${API_URL}/${id}`);
      if (response.ok) {
        const student = await response.json();
        resultDiv.innerHTML = `
          <div class="card mt-3 p-3">
            <h5>Student Details</h5>
            <p><strong>ID:</strong> ${student.id}</p>
            <p><strong>Name:</strong> ${student.name}</p>
            <p><strong>Course:</strong> ${student.course}</p>
            <p><strong>Email:</strong> ${student.email}</p>
            <p><strong>Phone:</strong> ${student.ph_no}</p>
            <p><strong>Address:</strong> ${student.address}</p>
          </div>`;
      } else {
        resultDiv.innerHTML = `<div class="alert alert-warning mt-3">⚠️ Student not found</div>`;
      }
    } catch (error) {
      console.error("Error searching student:", error);
      resultDiv.innerHTML = `<div class="alert alert-danger mt-3">❌ Error fetching student</div>`;
    }
  });
}

//PAGE: DELETE STUDENT 
if (currentPage() === "delete-student.html") {
  const deleteForm = document.getElementById("delete-student-form");
  const deleteMessage = document.getElementById("delete-message");

  deleteForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("delete-id").value;

    if (!id) {
      deleteMessage.innerHTML = `<div class="alert alert-warning">Please enter a student ID.</div>`;
      return;
    }

    if (!confirm(`Are you sure you want to delete student ID: ${id}?`)) {
      return;
    }

    try {
      const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
      if (response.ok) {
        deleteMessage.innerHTML = `<div class="alert alert-success">✅ Student ID ${id} deleted successfully.</div>`;
        deleteForm.reset();
      } else if (response.status === 404) {
        deleteMessage.innerHTML = `<div class="alert alert-warning">⚠️ No student found with ID ${id}.</div>`;
      } else {
        deleteMessage.innerHTML = `<div class="alert alert-danger">❌ Failed to delete student.</div>`;
      }
    } catch (error) {
      console.error("Error deleting student:", error);
      deleteMessage.innerHTML = `<div class="alert alert-danger">⚠️ Error while deleting student.</div>`;
    }
  });
}
