package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.services.EmailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestParam("token") String authorization,
                                                         @RequestParam List<String> receivers,
                                                         @RequestParam String subject,
                                                         @RequestParam String body,
                                                         @RequestParam Integer priority,
                                                         @RequestParam Optional<List<MultipartFile>> files) {
        Integer userId = getUserId(authorization);

        List<MultipartFile> attachments = new ArrayList<>();
        if (files.isPresent()) {
            attachments = files.get();
        }
        emailService.sendEmail(userId, receivers, subject, body, priority, attachments);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Email sent successfully!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteEmail(@RequestHeader("Authorization") String authorization,
                                                           @RequestParam Integer email_id) {
        Integer userId = getUserId(authorization);

        emailService.deleteEmail(userId, email_id);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Email deleted successfully!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/restore")
    public ResponseEntity<Map<String, Object>> restoreEmail(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam Integer email_id) {
        Integer userId = getUserId(authorization);

        emailService.restoreEmail(userId, email_id);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Email restored successfully!");
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
