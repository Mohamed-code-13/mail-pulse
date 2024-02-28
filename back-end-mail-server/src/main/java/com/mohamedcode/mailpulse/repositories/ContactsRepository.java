package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.Contact;

import java.util.List;

public interface ContactsRepository {

    List<Contact> getContacts(Integer userId);

    void createContact(Integer userId, String name, String email);

    void deleteContact(Integer userId, Integer contactId);

    void updateContact(Integer userId, Integer contactId, String name, String email);
    

}
