package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.models.UserModel;
import com.mohamedcode.mailpulse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserModel validateUser(String email, String password) throws CustomAuthException {
        if (email != null)
            email = email.toLowerCase();

        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public UserModel registerUser(String name, String email, String password) throws CustomAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null)
            email = email.toLowerCase();

        if (!pattern.matcher(email).matches())
            throw  new CustomAuthException("Invalid email format");

        Integer count = userRepository.getCountByEmail(email);
        if (count > 0)
            throw  new CustomAuthException("Email already in use");

        Integer userId = userRepository.create(name, email, password);
        return userRepository.findById(userId);
    }
}
