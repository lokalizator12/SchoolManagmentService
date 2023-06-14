package com.syberry.school.exceptions;

public class LoginIsExistException extends Exception {

    public LoginIsExistException() {
        super("Login is already exist");
    }
}
