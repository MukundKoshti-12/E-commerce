package com.scaler.userservice.services;

import com.scaler.userservice.exceptions.InvalidTokenException;
import com.scaler.userservice.exceptions.PasswordMismatchException;
import com.scaler.userservice.models.User;

public interface UserService {
    User signup(String name, String email, String password);
    String login(String email, String password) throws PasswordMismatchException;
    User validateToken(String token) throws InvalidTokenException;
}
