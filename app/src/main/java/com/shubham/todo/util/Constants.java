package com.shubham.todo.util;

public interface Constants {

    int PASSWORD_MIN_LENGTH = 6;
    int PASSWORD_MAX_LENGTH = 16;

    int ERROR_DB_INSERT = -1;

    int LOGOUT = -1;

    String ERROR_USER_NAME = "Invalid user name";
    String ERROR_PASS_LENGTH = "your password must be" + PASSWORD_MIN_LENGTH + "-" + PASSWORD_MAX_LENGTH + "characters";
    String ERROR_PASS_MATCH = "Confirmation password does not match the password";
    String ERROR_EMAIL_ID = "Invalid email address";
    String ERROR_EMAIL_ALREADY_EXIST = "This email already exists";
    String ERROR_INVALID_MAIL_PASS = "Invalid ID or Password";

}
