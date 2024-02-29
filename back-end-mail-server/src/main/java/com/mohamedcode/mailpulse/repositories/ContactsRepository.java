package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.Contact;

import java.util.List;

public interface ContactsRepository {

    List<Contact> getContacts(Integer userId);

    void createContactName(Integer userId, String name);

    Integer getContactId(Integer userId, String name);

    void createEmailContact(Integer userId, Integer contactId, String email);

    Integer contactCount(Integer userId, String name);

    Integer contactEmailCount(Integer contactId, String email);

    void deleteContact(Integer contactId);

    void deleteEmailContact(Integer contactId);

    void updateContactName(Integer contactId, String name);

}
