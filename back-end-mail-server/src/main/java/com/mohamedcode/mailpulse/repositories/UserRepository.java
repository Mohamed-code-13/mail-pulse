package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.models.UserModel;

public interface UserRepository {
    Integer create(String name, String email, String password) throws CustomAuthException;

    UserModel findByEmailAndPassword(String email, String password) throws CustomAuthException;

    Integer getCountByEmail(String email);

    UserModel findById(Integer userId);
}
