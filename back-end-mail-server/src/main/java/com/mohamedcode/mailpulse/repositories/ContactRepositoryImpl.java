package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactsRepository {

    private static final String SQL_GET_CONTACTS_PER_USER = """
            SELECT cnt.contact_id, cnt.contact_name, ecnt.email
            FROM contacts cnt
            LEFT JOIN email_contact ecnt
            	ON cnt.contact_id = ecnt.contact_id
            WHERE cnt.user_id = 
            """;
    private static final String SQL_COUNT_CONTACT_NAME_PER_USER = """
            SELECT COUNT(*)
            FROM contacts
            WHERE user_id = ? AND contact_name = ?;
            """;
    private static final String SQL_COUNT_EMAIL_PER_CONTACT = """
            SELECT COUNT(*)
            FROM email_contact
            WHERE contact_id = ? AND email = ?;
            """;
    private static final String SQL_FIND_CONTACT_ID_BY_NAME = """
            SELECT contact_id
            FROM contacts
            WHERE user_id = ? AND contact_name = ?;
            """;
    private static final String SQL_INSERT_CONTACT = """
            INSERT INTO contacts (user_id, contact_name)
            VALUES (?, ?);
            """;
    private static final String SQL_INSERT_EMAIL_CONTACT = """
            INSERT INTO email_contact (user_id, contact_id, email)
            VALUES (?, ?, ?);
            """;
    private static final String SQL_UPDATE_CONTACT_NAME = """
            UPDATE contacts
            SET contact_name = ?
            WHERE contact_id = ?
            """;
    private static final String SQL_DELETE_CONTACT = """
            DELETE FROM contacts
            WHERE contact_id = ?
            """;
    private static final String SQL_DELETE_EMAIL_CONTACT = """
            DELETE FROM email_contact
            WHERE contact_id = ?
            """;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> getContacts(Integer userId) {
        return jdbcTemplate.query(SQL_GET_CONTACTS_PER_USER + userId.toString(),
                BeanPropertyRowMapper.newInstance(Contact.class));
    }

    @Override
    public void createContactName(Integer userId, String name) {
        jdbcTemplate.update(SQL_INSERT_CONTACT, userId, name);
    }

    @Override
    public Integer getContactId(Integer userId, String name) {
        return jdbcTemplate.queryForObject(SQL_FIND_CONTACT_ID_BY_NAME, new Object[]{userId, name}, Integer.class);
    }

    @Override
    public void createEmailContact(Integer userId, Integer contactId, String email) {
        jdbcTemplate.update(SQL_INSERT_EMAIL_CONTACT, userId, contactId, email);
    }

    @Override
    public Integer contactCount(Integer userId, String name) {
        return jdbcTemplate.queryForObject(SQL_COUNT_CONTACT_NAME_PER_USER, new Object[]{userId, name}, Integer.class);
    }

    @Override
    public Integer contactEmailCount(Integer contactId, String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_EMAIL_PER_CONTACT, new Object[]{contactId, email}, Integer.class);
    }

    @Override
    public void deleteContact(Integer contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, contactId);
    }

    @Override
    public void deleteEmailContact(Integer contactId) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_CONTACT, contactId);
    }

    @Override
    public void updateContactName(Integer contactId, String name) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT_NAME, name, contactId);
    }
}
