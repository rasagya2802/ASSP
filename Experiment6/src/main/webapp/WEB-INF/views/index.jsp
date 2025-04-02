<!DOCTYPE html>
<html>
<head>
    <title>Employee Management</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#loadEmployees").click(function() {
                loadEmployees();
            });

            $("#addEmployee").click(function() {
                let name = $("#name").val();
                let email = $("#email").val();
                $.ajax({
                    url: "/api/employees/add",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({ name: name, email: email }),
                    success: function(response) {
                        loadEmployees();
                        $("#name").val("");
                        $("#email").val("");
                    }
                });
            });

            window.removeEmployee = function(id) {
                $.ajax({
                    url: `/api/employees/remove/${id}`,
                    type: "DELETE",
                    success: function(response) {
                        if (response.status === "success") {
                            loadEmployees();
                        }
                    }
                });
            };

            function loadEmployees() {
                $.get("/api/employees/load", function(data) {
                    $("#employeeList").empty();
                    data.forEach(emp => {
                        $("#employeeList").append(
                            `<li>${emp.name} (${emp.email}) 
                            <button onclick="removeEmployee(${emp.id})">Remove</button></li>`
                        );
                    });
                });
            }
        });
    </script>
</head>
<body>
    <h1>Employee Management</h1>
    <input type="text" id="name" placeholder="Enter Name">
    <input type="email" id="email" placeholder="Enter Email">
    <button id="addEmployee">Add Employee</button>
    <button id="loadEmployees">Load Employees</button>
    <h2>Employee List</h2>
    <ul id="employeeList"></ul>
</body>
</html>
