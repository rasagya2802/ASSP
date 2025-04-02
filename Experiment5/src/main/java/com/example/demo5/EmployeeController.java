package com.example.demo5;

import com.example.demo5.Employee;
import com.example.demo5.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/getEmployees")
    @ResponseBody
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/addEmployee")
    @ResponseBody
    public List<Employee> addEmployee(@RequestParam String name, @RequestParam String email) {
        employeeService.addEmployee(name, email);
        return employeeService.getAllEmployees();
    }

    @PostMapping("/deleteEmployee")
    @ResponseBody
    public List<Employee> deleteEmployee(@RequestParam int id) {
        employeeService.deleteEmployee(id);
        return employeeService.getAllEmployees();
    }
}