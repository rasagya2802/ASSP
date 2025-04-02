package com.example.demo2;

import jakarta.validation.constraints.*;

public class User{
    @NotBlank(message = "Name is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min=8, message = "Password must be at least 8 characters long")
    private String password;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }       

    public void setPassword(String password){
        this.password = password;
    }

}