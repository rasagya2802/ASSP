package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
     @GetMapping("/")
     public String hello(){
        return "Hello, this is Rasagya, this is my first backend program";
     }  
}
