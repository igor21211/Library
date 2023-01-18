package com.example.contactsbook.dao;


import com.example.contactsbook.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.util.List;

@RequiredArgsConstructor
@Component
public class PersonDao {


    private final JdbcTemplate jdbcTemplate;

    public static final RowMapper<Person> PERSON_ROW_MAPPER = (rs, i) -> Person.builder()
            .id(rs.getInt("person_id"))
            .name(rs.getString("name"))
            .surName(rs.getString("surname"))
            .secondName(rs.getString("second_name"))
            .age(rs.getInt("age"))
            .build();


    public static final String UPDATE_PERSON = "UPDATE person SET name=?, surname=?, second_name=?, age=? WHERE person_id=?";
    public static final String SELECT_ALL = "SELECT person_id, name, surname, second_name, age FROM person";

    public static final String ADD_PERSON = "INSERT INTO person (name, surname, second_name, age)VALUES (?,?,?,?)";

    public static final String SELECT_BY_ID = "SELECT person_id, name, surname, second_name, age FROM person WHERE person_id=?";

    public List<Person> getAllPerson(){
       return jdbcTemplate.query(SELECT_ALL,PERSON_ROW_MAPPER);
    }

    public int addPerson(Person person) {
        return jdbcTemplate.update(ADD_PERSON, ps -> {
            ps.setString(1,person.getName());
            ps.setString(2,person.getSurName());
            ps.setString(3,person.getSecondName());
            ps.setInt(4,person.getAge());
        });
    }


    public void deleteById(int id){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?",id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(UPDATE_PERSON, ps -> {
            ps.setString(1,person.getName());
            ps.setString(2,person.getSurName());
            ps.setString(3,person.getSecondName());
            ps.setInt(4,person.getAge());
            ps.setInt(5,id);
        });
    }



    public Person getById(int id) {
        return jdbcTemplate.query(SELECT_BY_ID, new Object[]{id}, PERSON_ROW_MAPPER)
                .stream()
                .findAny()
                .orElse(null);
    }
}
