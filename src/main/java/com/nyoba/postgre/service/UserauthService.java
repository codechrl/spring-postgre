package com.nyoba.postgre.service;

import com.nyoba.postgre.model.Userauth;

import java.util.List;

public interface UserauthService {

    public Userauth saveUser(Userauth userauth);
    public List<Userauth> getAllUser();


}
