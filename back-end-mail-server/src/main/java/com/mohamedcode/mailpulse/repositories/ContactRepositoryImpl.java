package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactsRepository {
    @Override
    public List<Contact> getContacts(Integer userId) {
        return null;
    }

    @Override
    public void createContactName(Integer userId, String name) {

    }

    @Override
    public Integer getContactId(Integer userId, String name) {
        return null;
    }

    @Override
    public void createEmailContact(Integer userId, Integer contactId, String email) {

    }

    @Override
    public Boolean contactExist(Integer userId, String name) {
        return null;
    }

    @Override
    public Boolean contactEmailExist(Integer userId, Integer contactId, String email) {
        return null;
    }

    @Override
    public void deleteContact(Integer userId, Integer contactId) {

    }

    @Override
    public void updateContactName(Integer userId, Integer contactId, String name) {

    }
}
