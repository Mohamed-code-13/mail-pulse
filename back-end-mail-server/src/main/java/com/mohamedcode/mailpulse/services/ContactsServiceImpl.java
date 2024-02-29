package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.models.Contact;
import com.mohamedcode.mailpulse.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    ContactsRepository contactsRepository;

    @Override
    public List<Map<String, Object>> getContacts(Integer userId) {
        List<Contact> contacts = contactsRepository.getContacts(userId);
        Map<Integer, Map<String, Object>> map = new HashMap<>();

        for (Contact contact : contacts) {
            Integer contactId = contact.getContact_id();
            String name = contact.getName();
            String email = contact.getEmail();

            if (!map.containsKey(contactId)) {
                map.put(contactId, new HashMap<>());
            }

            var curMap = map.get(contactId);
            curMap.put("contactId", contactId);
            curMap.put("name", name);

            if (!curMap.containsKey("emails"))
                curMap.put("emails", new ArrayList<String>());
            List<String> curEmails = (List<String>) curMap.get("emails");
            curEmails.add(email);
        }

        List<Map<String, Object>> res = new ArrayList<>();
        for (Integer key : map.keySet())
            res.add(map.get(key));

        return res;
    }

    @Override
    public void createContact(Integer userId, String name, List<String> emails) {
        if (contactsRepository.contactCount(userId, name) > 0)
            return;

        contactsRepository.createContactName(userId, name);
        Integer contactId = contactsRepository.getContactId(userId, name);

        for (String email : emails) {
            if (contactsRepository.contactEmailCount(contactId, email) == 0)
                contactsRepository.createEmailContact(userId, contactId, email);
        }
    }

    @Override
    public void deleteContact(List<Integer> contactIds) {
        for (Integer contactId : contactIds) {
            contactsRepository.deleteEmailContact(contactId);
            contactsRepository.deleteContact(contactId);
        }
    }

    @Override
    public void updateContact(Integer userId, Integer contactId, String name, List<String> emails) {
        contactsRepository.updateContactName(contactId, name);
        contactsRepository.deleteEmailContact(contactId);
        for (String email : emails) {
            contactsRepository.createEmailContact(userId, contactId, email);
        }
    }
}
