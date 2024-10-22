package com.mohamedcode.mailpulse.controllers;


import com.mohamedcode.mailpulse.Constants;
import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.services.ContactsService;
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
@RequestMapping("/contacts")
@CrossOrigin("*")
public class ContactsController {

    @Autowired
    ContactsService contactsService;

    @GetMapping("/getcontacts")
    public ResponseEntity<Map<String, Object>> getContacts(@RequestHeader("Authorization") String authorization) {
        Integer userId = getUserId(authorization);

        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> contacts = contactsService.getContacts(userId);
        map.put("contacts", contacts);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/createcontact")
    public ResponseEntity<Map<String, Object>> createContact(@RequestHeader("Authorization") String authorization,
                                                             @RequestBody Map<String, Object> body) {
        Integer userId = getUserId(authorization);

        String name = (String) body.get("name");
        List<String> emails = (List<String>) body.get("emails");

        contactsService.createContact(userId, name, emails);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Contact created successfully!");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/deletecontact")
    public ResponseEntity<Map<String, Object>> deleteContact(@RequestHeader("Authorization") String authorization,
                                                             @RequestParam("contactids") List<Integer> contactIds) {
        Integer userId = getUserId(authorization);

        contactsService.deleteContact(contactIds);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Contact deleted successfully!");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/updatecontact")
    public ResponseEntity<Map<String, Object>> updateContact(@RequestHeader("Authorization") String authorization,
                                                             @RequestBody Map<String, Object> body) {
        Integer userId = getUserId(authorization);

        Integer contactId = (Integer) body.get("contactId");
        String name = (String) body.get("name");
        List<String> emails = (List<String>) body.get("emails");

        contactsService.updateContact(userId, contactId, name, emails);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Contact updated successfully!");

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
