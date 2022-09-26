package com.nyoba.postgre.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {

    BCryptPasswordEncoder bCryptPasswordEncoder;
    SCryptPasswordEncoder sCryptPasswordEncoder;
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;
    @Value("${jwt.secret}")
    private String secret = "postgre";
    public static final long JWT_TOKEN_VALIDITY = 7 * 60 * 60;

    public Util() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sCryptPasswordEncoder = new SCryptPasswordEncoder();
        pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
    }

    // User
    public String getHashedBcrypt(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public String getHashedScrypt(String password){
        return sCryptPasswordEncoder.encode(password);
    }

    public String getHashedPbkdf2(String password){
        return pbkdf2PasswordEncoder.encode(password);
    }

    public boolean verifyBcrypt(String password, String hashed){
        return bCryptPasswordEncoder.matches(password, hashed);
    }

    public boolean verifyScrypt(String password, String hashed){
        return sCryptPasswordEncoder.matches(password, hashed);
    }

    public boolean verifyPbkdf2(String password, String hashed){
        return pbkdf2PasswordEncoder.matches(password, hashed);
    }


    // JWT
    private String GenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getJWT(Userauth userauth) {
        Map<String, Object> claims = new HashMap<>();
        return GenerateToken(claims, userauth.getEmail());
    }
}
