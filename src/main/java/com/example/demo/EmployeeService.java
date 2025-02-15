package com.example.demo;

import com.example.demo.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void addEmployee(String name, String email) {
        int id = counter.incrementAndGet();
        employees.add(new Employee(id, name, email));
    }

    public void deleteEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    public void updateEmployee(int id, String name, String email) {
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .ifPresent(employee -> {
                    employee.setName(name);
                    employee.setEmail(email);
                });
    }

    public void patchEmployee(int id, String name, String email) {
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .ifPresent(employee -> {
                    if (name != null && !name.isEmpty()) {
                        employee.setName(name); // Update name only if provided and not empty
                    }
                    if (email != null && !email.isEmpty()) {
                        employee.setEmail(email); // Update email only if provided and not empty
                    }
                });
    }
}