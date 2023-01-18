package com.example.contactsbook.controllers;

import com.example.contactsbook.dao.BookDao;
import com.example.contactsbook.dao.PersonDao;
import com.example.contactsbook.model.Book;
import com.example.contactsbook.model.Person;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainRestController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @GetMapping("/")
    public String main(){
        return "start";
    }

    @GetMapping("/person")
    public String getAllPeople(Model model){
    model.addAttribute("persons", personDao.getAllPerson());
    return "person";
    }

    @GetMapping("/book")
    public String getAllBook(Model model){
        model.addAttribute("books", bookDao.getAllBook());
        return "book";
    }

    @GetMapping("/book/{id}")
    public String getBookById(Model model, @ModelAttribute("person") Person person, @PathVariable int id){
        model.addAttribute("book", bookDao.getById(id));
        model.addAttribute("persons", bookDao.getBookByPersonIdForBook(id));
        model.addAttribute("personsAll", personDao.getAllPerson());
        return "book-id";
    }

    @GetMapping("/person/{id}")
    public String getPersonById(Model model , @PathVariable int id){
        model.addAttribute("person", personDao.getById(id));
        model.addAttribute("books", bookDao.getBookByPersonIdForPerson(id));
        return "person-id";
    }

    @DeleteMapping("/book/untie/{id}")
    public String untieBook(@PathVariable int id){
        bookDao.setUntieBook(id);
        return "redirect:/book/{id}";
    }

    @GetMapping("/add-person")
    public String addPerson(@ModelAttribute("person") Person person){
        return "add-person";
    }

    @PostMapping("/add-person")
    public String addP(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add-person";
        }else{
            personDao.addPerson(person);
            return "redirect:/person";
        }
    }

    @GetMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book){
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addB(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add-book";
        }else{
            bookDao.addBook(book);
            return "redirect:/book";
        }
    }

    @GetMapping("/edit-person/{id}")
    public String getEditPagePerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.getById(id));
        return "edit-person";
    }

    @PatchMapping("/edit-person/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult,
                             @PathVariable("id") int id)
    {
        if(bindingResult.hasErrors()){
            return "edit-person";
        }else {
            personDao.update(id,person);
            return "redirect:/person";
        }
    }

    @GetMapping("/edit-book/{id}")
    public String getEditPageBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.getById(id));
        return "edit-book";
    }

    @PatchMapping ("/edit-book/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                           @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "edit-book";
        }else{
            bookDao.update(id,book);
            return "redirect:/book";
        }
    }

    @PatchMapping("/book/{id}")
    public String addPersonForBook(@ModelAttribute("book") @PathVariable("id") int book_id, int id){
        bookDao.setPersonForBook(id,book_id);
        return "redirect:/book/{id}";
    }
}
