package com.example.contactsbook.dao;

import com.example.contactsbook.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ContactsDao {
    public static final RowMapper<Contact> CONTACT_ROW_MAPPER = (rs, i) -> Contact.builder()
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .email(rs.getString("email"))
            .phoneNumber(rs.getLong("phone_number"))
            .build();
    public static final String SELECT_ALL = "SELECT first_name, last_name, email, phone_number FROM contacts";
    private final NamedParameterJdbcTemplate jdbcTemplate;

   public List<Contact> getAll() {
        return jdbcTemplate.query(
                SELECT_ALL,
                CONTACT_ROW_MAPPER
        );
    }
}
