package com.mohamedcode.mailpulse.controllers;


import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
@CrossOrigin("*")
public class ContactsController {

    @GetMapping("/getcontacts")
    public ResponseEntity<Map<String, Object>> getContacts(@RequestParam("token") String authorization) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/createcontact")
    public ResponseEntity<Map<String, Object>> createContact(@RequestParam("token") String authorization,
                                                             @RequestBody Map<String, Object> body) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/deletecontact")
    public ResponseEntity<Map<String, Object>> deleteContact(@RequestParam("token") String authorization,
                                                             @RequestParam List<Integer> contactIds) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/updatecontact")
    public ResponseEntity<Map<String, Object>> updateContact(@RequestParam("token") String authorization,
                                                             @RequestBody Map<String, Object> body) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();
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
