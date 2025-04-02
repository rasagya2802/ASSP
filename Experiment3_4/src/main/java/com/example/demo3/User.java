package com.example.demo3;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

@NotBlank(message = "Username is required")
private String username;

@NotBlank(message = "Password is required")
@Size(min = 6, message = "Password must be at least 6 characters")
private String password;

// Getters and Setters
public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {

this.password = password;
}
}