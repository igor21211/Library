package com.example.contactsbook.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Person {

    int id;

    @NotEmpty(message = "name not should be Empty")
    @Size(min = 2,max = 30,message =  "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "name not should be Empty")
    @Size(min = 2,max = 30,message =  "SurName should be between 2 and 30 characters")
    private String surName;

    @NotEmpty(message = "secondName not should be Empty")
    @Size(min = 2,max = 30,message =  "SecondName should be between 2 and 30 characters")
    private String secondName;

    private int age;

}
