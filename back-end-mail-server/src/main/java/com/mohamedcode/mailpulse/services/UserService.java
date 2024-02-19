package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.models.UserModel;

public interface UserService {

    UserModel validateUser(String email, String password) throws CustomAuthException;

    UserModel registerUser(String name, String email, String password) throws CustomAuthException;
}
