package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.models.UserModel;
import com.mohamedcode.mailpulse.repositories.UserRepository;
import com.mohamedcode.mailpulse.services.HomeService;
import com.mohamedcode.mailpulse.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/getuser")
    public ResponseEntity<Map<String, Object>> getUser(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("user_id");
        UserModel user = homeService.getUserById(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUserId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/getinbox")
    public ResponseEntity<List<Map<String, Object>>> getInbox(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("user_id");
        var inbox = homeService.getInbox(userId);
        return new ResponseEntity<>(inbox, HttpStatus.OK);
    }
}
