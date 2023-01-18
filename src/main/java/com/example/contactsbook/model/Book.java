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
public class Book {

    int id;

    @NotEmpty(message = "name of book not be empty")
    private String nameBook;
    @NotEmpty(message = "Field Author not be Empty")
    private String author;

}
