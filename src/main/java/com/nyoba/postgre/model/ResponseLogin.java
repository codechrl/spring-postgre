package com.nyoba.postgre.model;

public class ResponseLogin {

    private String message;
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Userauth userauth;

    public ResponseLogin(){
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Userauth getUserauth() {
        return userauth;
    }

    public void setUserauth(Userauth userauth) {
        this.userauth = userauth;
    }
}
