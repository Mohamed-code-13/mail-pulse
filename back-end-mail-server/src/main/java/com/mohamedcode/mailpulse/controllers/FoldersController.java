package com.mohamedcode.mailpulse.controllers;

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
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/createfolder")
    public ResponseEntity<Map<String, Object>> createFolder(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/deletefolder")
    public ResponseEntity<Map<String, Object>> deleteFolder(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/folderemails")
    public ResponseEntity<Map<String, Object>> getFolderEmails(@RequestHeader("Authorization") String authorization,
                                                            @RequestParam("foldername") String folderName) {
        Map<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
