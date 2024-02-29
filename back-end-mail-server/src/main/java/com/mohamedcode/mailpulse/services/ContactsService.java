package com.mohamedcode.mailpulse.services;

import java.util.List;
import java.util.Map;

public interface ContactsService {

    List<Map<String, Object>> getContacts(Integer userId);

    void createContact(Integer userId, String name, List<String> emails);

    void deleteContact(Integer userId, List<Integer> contactIds);

    void updateContact(Integer userId, Integer contactId, String name, List<String> emails);
    
}
