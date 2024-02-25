package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/folders")
@CrossOrigin("*")
public class FoldersController {

    @GetMapping("/getfolders")
    public ResponseEntity<Map<String, Object>> getFolders(@RequestHeader("Authorization") String authorization) {
        int userId = getUserId(authorization);
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/createfolder")
    public ResponseEntity<Map<String, Object>> createFolder(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        int userId = getUserId(authorization);
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/deletefolder")
    public ResponseEntity<Map<String, Object>> deleteFolder(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        int userId = getUserId(authorization);
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/folderemails")
    public ResponseEntity<Map<String, Object>> getFolderEmails(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        int userId = getUserId(authorization);
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
