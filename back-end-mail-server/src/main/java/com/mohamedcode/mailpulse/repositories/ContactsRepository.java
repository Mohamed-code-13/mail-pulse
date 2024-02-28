package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.Contact;

import java.util.List;

public interface ContactsRepository {

    List<Contact> getContacts(Integer userId);

    void createContactName(Integer userId, String name);

    Integer getContactId(Integer userId, String name);

    void createEmailContact(Integer userId, Integer contactId, String email);

    Boolean contactExist(Integer userId, String name);

    Boolean contactEmailExist(Integer userId, Integer contactId, String email);

    void deleteContact(Integer userId, Integer contactId);

    void updateContactName(Integer userId, Integer contactId, String name);

}
