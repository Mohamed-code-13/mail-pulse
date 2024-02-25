package com.mohamedcode.mailpulse.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private static final String SQL_MOVE_EMAIL_TO_TRASH = """
            UPDATE emails
            SET deleted = TRUE,
                deleted_at = CURRENT_TIMESTAMP
            WHERE email_id = ?
            """;
    private static final String SQL_IS_EMAIL_IN_TRASH = "SELECT deleted FROM emails WHERE email_id = ?";
    private static final String SQL_DELETE_EMAIL_BY_ID = "DELETE FROM emails WHERE email_id = ?";
    private static final String SQL_GET_USER_ID_BY_EMAIL = "SELECT user_id FROM users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void deleteEmail(Integer emailID) {
        if (isEmailInTrash(emailID))
            deleteEmailFromDB(emailID);
        else
            moveEmailToTrash(emailID);
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

    private void moveEmailToTrash(Integer emailId) {
        jdbcTemplate.update(SQL_MOVE_EMAIL_TO_TRASH, emailId);
    }

    private void deleteEmailFromDB(Integer emailId) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_BY_ID, emailId);
    }

    private Boolean isEmailInTrash(Integer emailId) {
        return jdbcTemplate.queryForObject(SQL_IS_EMAIL_IN_TRASH, new Object[]{emailId}, Boolean.class);
    }
}
