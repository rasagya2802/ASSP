package com.example.demo5;

import com.example.demo5.Employee;
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
}