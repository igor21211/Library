package com.example.contactsbook.controllers;

import com.example.contactsbook.dao.ContactsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
