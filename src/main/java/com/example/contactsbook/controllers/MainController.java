package com.example.contactsbook.controllers;

import com.example.contactsbook.dao.ContactsDao;
import com.example.contactsbook.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final ContactsDao contactsDao;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("contacts",contactsDao.getAll());
        return "main";
    }

    @GetMapping("/add-contact")
    public String addContact(){
        return "add-contact";
    }
    @PostMapping("/add-contact")
    public String add(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName")String lastName,
                       @RequestParam("email") String email,
                       @RequestParam("phoneNumber") Long phoneNumber){
       Contact contact = new Contact( firstName,lastName,email,phoneNumber);
        int status = contactsDao.addContact(contact);
        if(status< 1){
            return "Erorr";
        }else {
            return "redirect:/";
        }
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        contactsDao.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("contact",contactsDao.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("contact") Contact contact, @PathVariable("id") int id){
        contactsDao.update(id,contact);
        return "redirect:/";
    }


}
