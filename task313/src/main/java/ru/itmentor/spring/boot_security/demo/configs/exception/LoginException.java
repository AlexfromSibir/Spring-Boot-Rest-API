package ru.itmentor.spring.boot_security.demo.configs.exception;

import java.io.Serializable;

public class LoginException implements Serializable {
    private static final long serialVersionUID = 1696667646977159400L;

    private final String message;
    private String email;
    private String password;

    public LoginException(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }
}
