package com.example.contactsbook.dao;

import com.example.contactsbook.model.Book;
import com.example.contactsbook.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, i) -> Book.builder()
            .id(rs.getInt("book_id"))
            .nameBook(rs.getString("name_book"))
            .author(rs.getString("author"))
            .build();

    public static final RowMapper<Person> PERSON_ROW_MAPPER = (rs, i) -> Person.builder()
            .name(rs.getString("name"))
            .surName(rs.getString("surname"))
            .secondName(rs.getString("second_name"))
            .age(rs.getInt("age"))
            .build();

    public static final String SELECT_ALL = "SELECT book_id,name_book, author FROM book";

    public static final String ADD_BOOK = "INSERT INTO book (name_book, author)VALUES (?,?)";

    public static final String SELECT_BY_ID_FOR_PERSON = "SELECT book_id, name_book, author FROM book WHERE person_id=?";
    public static final String SELECT_BY_ID = "SELECT book_id, name_book, author FROM book WHERE book_id=?";
    public static final String SELECT_BY_ID_FOR_BOOK = "SELECT  name,surname,second_name,age from person JOIN book ON person.person_id = book.person_id where book_id = ?;";
    public static final String UPDATE_BOOK = "UPDATE book SET name_book=?, author=? WHERE book_id=?";


    public static final String UNTIE_BOOK = "UPDATE book SET person_id = null where book_id = ?";
    public static final String SET_BOOK_FOR_PERSON = "UPDATE book SET person_id = ? where book_id = ?";

    public List<Book> getAllBook(){
        return jdbcTemplate.query(SELECT_ALL,BOOK_ROW_MAPPER);
    }

    public int addBook(Book book){
        return jdbcTemplate.update(ADD_BOOK, ps -> {
            ps.setString(1,book.getNameBook());
            ps.setString(2,book.getAuthor());
        });
    }


    public void update(int id, Book book) {
        jdbcTemplate.update(UPDATE_BOOK, ps -> {
            ps.setString(1,book.getNameBook());
            ps.setString(2,book.getAuthor());
            ps.setInt(3,id);
        });
    }

    public Book getById(int id){
        return jdbcTemplate.query(SELECT_BY_ID,new Object[]{id},BOOK_ROW_MAPPER)
                .stream()
                .findAny()
                .orElse(null);
    }
    public List<Book> getBookByPersonIdForPerson(int id){
        return jdbcTemplate.query(SELECT_BY_ID_FOR_PERSON,new Object[]{id},BOOK_ROW_MAPPER);
    }

    public List<Person> getBookByPersonIdForBook(int id){
        return jdbcTemplate.query(SELECT_BY_ID_FOR_BOOK,new Object[]{id},PERSON_ROW_MAPPER);
    }

    public void deleteById(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?",id);
    }

    public int setUntieBook(int id){
        return jdbcTemplate.update(UNTIE_BOOK,id);
    }

    public void setPersonForBook(int person_id, int book_id){
        jdbcTemplate.update(SET_BOOK_FOR_PERSON,person_id,book_id);
    }
}



