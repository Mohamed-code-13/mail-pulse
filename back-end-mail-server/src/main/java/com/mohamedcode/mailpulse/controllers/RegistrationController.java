package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.models.UserModel;
import com.mohamedcode.mailpulse.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUpUser(@RequestBody Map<String, Object> userMap) {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        UserModel user = userService.registerUser(name, email, password);
        Map<String, String> map = generateJWTToken(user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> logInUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        UserModel user = userService.validateUser(email, password);
        Map<String, String> map = generateJWTToken(user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(UserModel userModel) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET_API_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("user_id", userModel.getUserId())
                .claim("name", userModel.getName())
                .claim("email", userModel.getEmail())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
