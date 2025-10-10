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

		// 1️⃣ Collect form values
		const name = document.getElementById("name").value.trim();
		const course = document.getElementById("course").value.trim();
		const email = document.getElementById("email").value.trim();
		const phNo = document.getElementById("ph_no").value.trim();
		const address = document.getElementById("address").value.trim();

		// 2️⃣ Validation Section
		if (!/^[A-Za-z\s]+$/.test(name)) {
			alert("⚠️ Enter a valid name (letters and spaces only).");
			return;
		}

		if (course === "") {
			alert("⚠️ Course cannot be empty.");
			return;
		}

		// Email validation
		const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!emailPattern.test(email)) {
			alert("⚠️ Enter a valid email address.");
			return;
		}

		// Phone number validation (Indian 10-digit format)
		if (!/^[6-9][0-9]{9}$/.test(phNo)) {
			alert("⚠️ Enter a valid 10-digit phone number starting with 6–9.");
			return;
		}

		if (address.length < 3) {
			alert("⚠️ Address must be at least 3 characters long.");
			return;
		}

		// 3️⃣ If all validations pass, prepare object
		const student = { name, course, email, ph_no: phNo, address };

		try {
			const response = await fetch(API_URL, {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify(student),
			});

			if (response.ok) {
				alert("✅ Student added successfully!");
				studentForm.reset();
			} else if (response.status === 400) {
				const errorData = await response.json();
				alert(`⚠️ ${errorData.message || "Validation failed!"}`);
			} else {
				alert("❌ Failed to add student. Please try again.");
			}
		} catch (error) {
			console.error("Error:", error);
			alert("⚠️ Error while adding student. Check console for details.");
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

// PAGE: UPDATE STUDENT
if (currentPage() === "update-student.html") {
	const updateForm = document.getElementById("update-student-form");
	const fetchBtn = document.getElementById("fetchStudent");
	const updateFieldBtn = document.getElementById("updateField");

	// 🔹 1️⃣ Fetch student by ID and fill the form
	fetchBtn.addEventListener("click", async () => {
		const id = document.getElementById("studentId").value;
		if (!id) return alert("⚠️ Please enter a student ID.");

		try {
			const res = await fetch(`${API_URL}/id/${id}`);
			if (!res.ok) {
				alert("❌ Student not found!");
				return;
			}

			const student = await res.json();
			document.getElementById("update-id").value = student.id;
			document.getElementById("update-name").value = student.name;
			document.getElementById("update-course").value = student.course;
			document.getElementById("update-email").value = student.email;
			document.getElementById("update-ph_no").value = student.ph_no;
			document.getElementById("update-address").value = student.address;

			alert("✅ Student data loaded successfully!");
		} catch (error) {
			console.error("Error fetching student:", error);
			alert("⚠️ Error while fetching student data.");
		}
	});

	// 🔹 2️⃣ Full update using PUT (updates all fields)
	updateForm.addEventListener("submit", async (e) => {
		e.preventDefault();

		const id = document.getElementById("update-id").value;
		if (!id) return alert("⚠️ Please enter or fetch a student first.");

		const student = {
			name: document.getElementById("update-name").value,
			course: document.getElementById("update-course").value,
			email: document.getElementById("update-email").value,
			ph_no: document.getElementById("update-ph_no").value,
			address: document.getElementById("update-address").value,
		};

		try {
			const res = await fetch(`${API_URL}/${id}`, {
				method: "PUT",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify(student),
			});

			if (res.ok) {
				alert("✅ Student updated successfully!");
				updateForm.reset();
			} else {
				alert("❌ Failed to update student.");
			}
		} catch (error) {
			console.error("Error updating student:", error);
			alert("⚠️ Error while updating student.");
		}
	});
// 🔹 3️⃣ Partial update for one specific field (PATCH)
			updateFieldBtn.addEventListener("click", async () => {
				const id = document.getElementById("studentId").value;
				const field = document.getElementById("fieldToUpdate").value;
				const value = document.getElementById("newValue").value.trim();

				if (!id) return alert("⚠️ Please enter student ID first.");
				if (!value) return alert("⚠️ Please enter a new value.");

				try {
					const res = await fetch(`${API_URL}/${id}/update`, {
						method: "PATCH",
						headers: { "Content-Type": "application/json" },
						body: JSON.stringify({ [field]: value }), // dynamic field update
					});

					if (res.ok) {
						alert("✅ Field updated successfully!");
						document.getElementById("newValue").value = "";
					} else {
						const errorData = await res.json();
						alert("❌ Update failed: " + (errorData.message || "Unknown error"));
					}
				} catch (error) {
					console.error("Error updating field:", error);
					alert("⚠️ Error while updating specific field.");
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
					const response = await fetch(`${API_URL}/id/${id}`);
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

			const findFormName = document.getElementById("find-student-form-name");
			const resultDivName = document.getElementById("search-result-name");

			findFormName.addEventListener("submit", async (e) => {
				e.preventDefault();
				const name = document.getElementById("find-student-name").value;

				try {
					const response = await fetch(`${API_URL}/name/${name}`);
					if (response.ok) {
						const student = await response.json();
						resultDivName.innerHTML = `
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
						resultDivName.innerHTML = `<div class="alert alert-warning mt-3">⚠️ Student not found</div>`;
					}
				} catch (error) {
					console.error("Error searching student:", error);
					resultDivName.innerHTML = `<div class="alert alert-danger mt-3">❌ Error fetching student</div>`;
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
