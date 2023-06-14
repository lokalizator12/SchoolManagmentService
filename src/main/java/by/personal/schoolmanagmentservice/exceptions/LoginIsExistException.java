package by.personal.schoolmanagmentservice.exceptions;

public class LoginIsExistException extends Exception {

    public LoginIsExistException() {
        super("Login is already exist");
    }
}
