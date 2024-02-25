package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.models.UserModel;
import com.mohamedcode.mailpulse.services.HomeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/getuser")
    public ResponseEntity<Map<String, Object>> getUser(@RequestHeader("Authorization") String authorization) {
//        int userId = (Integer) request.getAttribute("user_id");
        Integer userId = getUserId(authorization);
        UserModel user = homeService.getUserById(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUserId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/inbox")
    public ResponseEntity<Map<String, Object>> getInbox(@RequestHeader("Authorization") String authorization,
                                                        @RequestParam Integer sort,
                                                        @RequestParam Integer page) {
        int userId = getUserId(authorization);
        var inbox = homeService.getInbox(userId, sort, page);
        Map<String, Object> map = new HashMap<>();
        map.put("list", inbox);
        map.put("current", 1);
        map.put("total", 2);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/sent")
    public ResponseEntity<Map<String, Object>> getSent(@RequestHeader("Authorization") String authorization,
                                                       @RequestParam Integer sort,
                                                       @RequestParam Integer page) {
        int userId = getUserId(authorization);
        var sent = homeService.getSent(userId, sort, page);
        Map<String, Object> map = new HashMap<>();
        map.put("list", sent);
        map.put("current", 1);
        map.put("total", 2);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/trash")
    public ResponseEntity<Map<String, Object>> getTrash(@RequestHeader("Authorization") String authorization,
                                                       @RequestParam Integer sort,
                                                       @RequestParam Integer page) {
        int userId = getUserId(authorization);
        var trash = homeService.getTrash(userId, sort, page);
        Map<String, Object> map = new HashMap<>();
        map.put("list", trash);
        map.put("current", 1);
        map.put("total", 2);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Integer getUserId(String authorization) {
        String[] authHeaders = authorization.split("Bearer ");

        String token = authHeaders[1];
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Constants.SECRET_API_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return Integer.parseInt(claims.get("user_id").toString());
        } catch (Exception e) {
            throw new CustomAuthException("invalid/expired token");
        }
    }
}
