package com.example.contactsbook.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Contact {

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
}
