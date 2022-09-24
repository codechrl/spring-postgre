package com.nyoba.postgre.repository;

import com.nyoba.postgre.model.Userauth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserauthRepository extends JpaRepository<Userauth, String> {

}
