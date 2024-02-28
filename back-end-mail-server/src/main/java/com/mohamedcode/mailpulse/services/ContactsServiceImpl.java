package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    ContactsRepository contactsRepository;

    @Override
    public Map<String, Object> getContacts(Integer userId) {
        return null;
    }

    @Override
    public void createContact(Integer userId, String name, List<String> emails) {
        if (contactsRepository.contactExist(userId, name))
            return;

        contactsRepository.createContactName(userId, name);
        Integer contactId = contactsRepository.getContactId(userId, name);

        for (String email : emails) {
            if (!contactsRepository.contactEmailExist(userId, contactId, email))
                contactsRepository.createEmailContact(userId, contactId, email);
        }
    }

    @Override
    public void deleteContact(Integer userId, List<Integer> contactIds) {
        for (Integer contactId : contactIds)
            contactsRepository.deleteContact(userId, contactId);
    }

    @Override
    public void updateContact(Integer userId, Integer contactId, String name, List<String> emails) {
        if (!contactsRepository.contactExist(userId, name))
            return;

        contactsRepository.updateContactName(userId, contactId, name);
        for (String email : emails) {
            if (!contactsRepository.contactEmailExist(userId, contactId, email))
                contactsRepository.createEmailContact(userId, contactId, email);
        }
    }
}
