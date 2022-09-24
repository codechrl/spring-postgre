package com.nyoba.postgre.controller;

import com.nyoba.postgre.model.Userauth;
import com.nyoba.postgre.service.UserauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserauthController {

    @Autowired
    private UserauthService userauthService;

    @PostMapping("/add")
    public String add(@RequestBody Userauth userauth){
        userauthService.saveUser(userauth);
        return "New user is added";
    }

    @GetMapping("/getAll")
    public List<Userauth> getAllUser(){
        return userauthService.getAllUser();
    }
}
