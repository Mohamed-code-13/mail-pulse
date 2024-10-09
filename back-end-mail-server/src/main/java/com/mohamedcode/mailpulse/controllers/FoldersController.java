package com.mohamedcode.mailpulse.controllers;

import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.services.FolderService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/folders")
@CrossOrigin("*")
public class FoldersController {

    @Autowired
    FolderService folderService;

    @GetMapping("/getfolders")
    public ResponseEntity<Map<String, Object>> getFolders(@RequestHeader("Authorization") String authorization) {
        int userId = getUserId(authorization);
        var folders = folderService.getFolderNames(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("folders", folders);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/createfolder")
    public ResponseEntity<Map<String, Object>> createFolder(@RequestHeader("Authorization") String authorization,
                                                            @RequestBody Map<String, Object> body) {
        int userId = getUserId(authorization);
        String folderName = (String) body.get("foldername");

        folderService.createFolder(userId, folderName);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Folder created successfully!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/deletefolder")
    public ResponseEntity<Map<String, Object>> deleteFolder(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        int userId = getUserId(authorization);

        folderService.deleteFolder(userId, folderName);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Folder deleted Successfully!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/folderemails")
    public ResponseEntity<Map<String, Object>> getFolderEmails(@RequestHeader("Authorization") String authorization,
                                                               @RequestParam("foldername") String folderName,
                                                               @RequestParam Integer sort,
                                                               @RequestParam Integer page) {
        int userId = getUserId(authorization);
        var emails = folderService.getEmailsByFolder(userId, folderName, sort, page);

        Map<String, Object> map = new HashMap<>();
        map.put("list", emails);
        map.put("current", 1);
        map.put("total", 2);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/moveemail")
    public ResponseEntity<Map<String, Object>> moveEmail(@RequestHeader("Authorization") String authorization,
                                                         @RequestBody Map<String, Object> body) {
        int userId = getUserId(authorization);
        List<Integer> emailIds = (List<Integer>)  body.get("id");
        String folderName = (String) body.get("foldername");

        folderService.moveEmails(userId, emailIds, folderName);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Email moved successfully!");

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
