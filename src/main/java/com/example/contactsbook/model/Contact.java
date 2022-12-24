package com.example.contactsbook.model;

import lombok.*;
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;

    public Contact(String firstName, String lastName, String email, Long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
