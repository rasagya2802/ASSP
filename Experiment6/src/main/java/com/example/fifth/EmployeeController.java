package com.example.fifth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final Map<Integer, Map<String, String>> employees = new HashMap<>();
    private final NavigableSet<Integer> deletedIds = new TreeSet<>();

    private int currentId = 1;

    // Load employees
    @GetMapping("/load")
    public List<Map<String, String>> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    // Add employee
    @PostMapping("/add")
    public Map<String, String> addEmployee(@RequestBody Map<String, String> request) {
        int idToUse = getNextAvailableId();
        Map<String, String> employee = new HashMap<>();
        employee.put("id", String.valueOf(idToUse));
        employee.put("name", request.get("name"));
        employee.put("email", request.get("email"));
        employees.put(idToUse, employee);

        return employee;
    }

    // Update employee (PUT)
    @PutMapping("/update/{id}")
    public Map<String, String> updateEmployee(@PathVariable int id, @RequestBody Map<String, String> request) {
        if (!employees.containsKey(id)) {
            return Collections.singletonMap("status", "Employee not found");
        }

        Map<String, String> employee = employees.get(id);
        employee.put("name", request.get("name"));
        employee.put("email", request.get("email"));

        return employee;
    }

    // Partially update employee (PATCH)
    @PatchMapping("/update/{id}")
    public Map<String, String> patchEmployee(@PathVariable int id, @RequestBody Map<String, String> request) {
        if (!employees.containsKey(id)) {
            return Collections.singletonMap("status", "Employee not found");
        }

        Map<String, String> employee = employees.get(id);
        if (request.containsKey("name")) {
            employee.put("name", request.get("name"));
        }
        if (request.containsKey("email")) {
            employee.put("email", request.get("email"));
        }

        return employee;
    }

    // Remove employee by ID
    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeEmployee(@PathVariable int id) {
        if (employees.containsKey(id)) {
            employees.remove(id);
            deletedIds.add(id);
            return Collections.singletonMap("status", "success");
        } else {
            return Collections.singletonMap("status", "error");
        }
    }

    // Get the next available ID
    private int getNextAvailableId() {
        if (!deletedIds.isEmpty()) {
            return deletedIds.pollFirst(); // Return the smallest available ID from deletedIds
        } else {
            return currentId++; // Otherwise, return the next sequential ID
        }
    }
}
