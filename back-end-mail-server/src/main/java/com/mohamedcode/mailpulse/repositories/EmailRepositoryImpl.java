package com.mohamedcode.mailpulse.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {
    private static final String SQL_INSERT_EMAIL = """
            INSERT INTO emails
            	(sender_id,
            	 receiver_id,
            	 subject,
            	 body,
            	 priority,
            	 sent_date)
            VALUES (
            	?, ?, ?, ?, ?, ?
            );
            """;
    private static final String SQL_DELETE_EMAIL_BY_ID = "DELETE FROM emails WHERE email_id = ?";
    private static final String SQL_GET_USER_ID_BY_EMAIL = "SELECT user_id FROM users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void deleteEmail(Integer emailID) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_BY_ID, emailID);
    }

    @Override
    public void sendEmail(Integer senderId, String receiver,
                          String subject, String body, Integer priority,
                          List<MultipartFile> files) {
        Integer receiverId = getUserIdByEmail(receiver);
        String sent_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());

        jdbcTemplate.update(SQL_INSERT_EMAIL, senderId, receiverId, subject, body, priority, sent_date);
    }

    private Integer getUserIdByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_ID_BY_EMAIL, new Object[]{email}, Integer.class);
    }
}
