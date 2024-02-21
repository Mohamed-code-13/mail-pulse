package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class EmailController {

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestHeader("Authorization") String authorization,
                                                         @RequestParam List<String> receivers,
                                                         @RequestParam String subject,
                                                         @RequestParam String body,
                                                         @RequestParam Integer priority,
                                                         @RequestParam Optional<List<MultipartFile>> files) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteEmail(@RequestHeader("Authorization") String authorization,
                                                           @RequestParam Integer email_id) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "email_id: " + email_id);
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
