package com.nyoba.postgre.controller;

import com.nyoba.postgre.model.ResponseLogin;
import com.nyoba.postgre.model.Userauth;
import com.nyoba.postgre.model.Util;
import com.nyoba.postgre.service.UserauthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserauthController {

    @Autowired
    private UserauthService userauthService;
    private Util util = new Util();

    @Tag(name = "user")
    @GetMapping("/")
    public List<Userauth> getAllUser(){
        return userauthService.getAllUser();
    }
    //
    @Tag(name = "user")
    @GetMapping("/a")
    public List<Userauth> getAllUser(){
        return userauthService.getAllUser();
    }

    @Tag(name = "user")
    @GetMapping("/{email}")
    public Optional<Userauth> getUser(String email){
        return userauthService.getUser(email);
    }

    @Tag(name = "user")
    @PostMapping("/add")
    public String add(@RequestBody Userauth userauth){
        String hashed = util.getHashedBcrypt(userauth.getPassword());
        userauth.setPassword(hashed);
        userauthService.saveUser(userauth);
        return "New user is added";
    }

    @Tag(name = "auth")
    @PostMapping("/login/{email}&{password}")
    public ResponseLogin login(String email, String password){
        Optional<Userauth> userauth =  userauthService.getUser(email);
        ResponseLogin response = new ResponseLogin();
        response.setMessage("username or password is incorrect");
        if (userauth.isPresent()) {
            if (util.verifyBcrypt(password, userauth.get().getPassword())) {
                response.setToken(util.getJWT(userauth.get()));
                response.setUserauth(userauth.get());
                response.setMessage("success");
                return response;
            }
        }
        return response;
    }
}
