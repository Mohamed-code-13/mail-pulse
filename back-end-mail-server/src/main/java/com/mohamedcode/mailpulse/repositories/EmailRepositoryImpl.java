package com.mohamedcode.mailpulse.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {
    private static final String SQL_DELETE_EMAIL_BY_ID = "DELETE FROM emails WHERE email_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void deleteEmail(Integer emailID) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_BY_ID, emailID);
    }

    @Override
    public void sendEmail(Integer senderId, List<String> receivers,
                          String subject, String body, Integer priority,
                          List<MultipartFile> files) {

    }
}
