package com.nyoba.postgre.service;

import com.nyoba.postgre.model.Userauth;
import com.nyoba.postgre.repository.UserauthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserauthServiceImplementation implements UserauthService {

    @Autowired
    private UserauthRepository userauthRepository;

    @Override
    public Userauth saveUser(Userauth userauth) {
        return userauthRepository.save(userauth);
    }

    @Override
    public List<Userauth> getAllUser() {
        return userauthRepository.findAll();
    }

    @Override
    public Optional<Userauth> getUser(String email) {
       return userauthRepository.findById(email);
    }

}
