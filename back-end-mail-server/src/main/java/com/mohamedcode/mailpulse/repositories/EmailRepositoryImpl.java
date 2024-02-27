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
    private static final String SQL_MOVE_EMAIL_TO_SENDER_TRASH = """
            UPDATE emails
            SET deleted_by_sender = TRUE
            WHERE email_id = ?
            """;
    private static final String SQL_MOVE_EMAIL_TO_RECEIVER_TRASH = """
            UPDATE emails
            SET deleted_by_receiver = TRUE
            WHERE email_id = ?
            """;
    private static final String SQL_DELETE_EMAIL_FROM_SENDER_TRASH = """
            UPDATE emails
            SET deleted_by_sender = NULL
            WHERE email_id = ?
            """;
    private static final String SQL_DELETE_EMAIL_FROM_RECEIVER_TRASH = """
            UPDATE emails
            SET deleted_by_receiver = NULL
            WHERE email_id = ?
            """;
    private static final String SQL_EMAIL_IN_SENDER_TRASH = "SELECT deleted_by_sender FROM emails WHERE email_id = ?";
    private static final String SQL_EMAIL_IN_RECEIVER_TRASH = "SELECT deleted_by_receiver FROM emails WHERE email_id = ?";
    private static final String SQL_GET_EMAIL_SENDER = "SELECT sender_id FROM emails WHERE email_id = ?";
    private static final String SQL_GET_EMAIL_RECEIVER = "SELECT receiver_id FROM emails WHERE email_id = ?";
    private static final String SQL_DELETE_EMAIL_BY_ID = "DELETE FROM emails WHERE email_id = ?";
    private static final String SQL_GET_USER_ID_BY_EMAIL = "SELECT user_id FROM users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void deleteEmail(Integer userId, Integer emailID) {
        if (userId.equals(getEmailSender(emailID))) {
            if (isEmailInSenderTrash(emailID))
                deleteEmailFromSenderTrash(emailID);
            else
                moveEmailToSenderTrash(emailID);
        } else if (userId.equals(getEmailReceiver(emailID))) {
            if (isEmailInReceiverTrash(emailID))
                deleteEmailFromReceiverTrash(emailID);
            else
                moveEmailToReceiverTrash(emailID);
        }

        if (isEmailInSenderTrash(emailID) == null && isEmailInReceiverTrash(emailID) == null)
            deleteEmailFromDB(emailID);
    }

    @Override
    public void restoreEmail(Integer userId, Integer emailID) {

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

    private Integer getEmailSender(Integer emailId) {
        return jdbcTemplate.queryForObject(SQL_GET_EMAIL_SENDER, new Object[]{emailId}, Integer.class);
    }

    private Integer getEmailReceiver(Integer emailId) {
        return jdbcTemplate.queryForObject(SQL_GET_EMAIL_RECEIVER, new Object[]{emailId}, Integer.class);
    }

    private Boolean isEmailInSenderTrash(Integer emailId) {
        return jdbcTemplate.queryForObject(SQL_EMAIL_IN_SENDER_TRASH, new Object[]{emailId}, Boolean.class);
    }

    private Boolean isEmailInReceiverTrash(Integer emailId) {
        return jdbcTemplate.queryForObject(SQL_EMAIL_IN_RECEIVER_TRASH, new Object[]{emailId}, Boolean.class);
    }

    private void deleteEmailFromSenderTrash(Integer emailId) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_FROM_SENDER_TRASH, emailId);
    }

    private void deleteEmailFromReceiverTrash(Integer emailId) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_FROM_RECEIVER_TRASH, emailId);
    }

    private void moveEmailToSenderTrash(Integer emailId) {
        jdbcTemplate.update(SQL_MOVE_EMAIL_TO_SENDER_TRASH, emailId);
    }

    private void moveEmailToReceiverTrash(Integer emailId) {
        jdbcTemplate.update(SQL_MOVE_EMAIL_TO_RECEIVER_TRASH, emailId);
    }

    private void deleteEmailFromDB(Integer emailId) {
        jdbcTemplate.update(SQL_DELETE_EMAIL_BY_ID, emailId);
    }
}
