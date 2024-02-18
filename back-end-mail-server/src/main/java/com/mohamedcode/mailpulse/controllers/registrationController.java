package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.models.UserModel;
import com.mohamedcode.mailpulse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class registrationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUpUser(@RequestBody Map<String, Object> userMap) {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        UserModel user = userService.registerUser(name, email, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Registered successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> logInUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        UserModel userModel = userService.validateUser(email, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Logged In Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
