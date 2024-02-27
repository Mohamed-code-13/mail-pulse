package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.EmailModel;
import com.mohamedcode.mailpulse.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeRepositoryImpl implements HomeRepository {

    private static final String SQL_GET_EMAILS_BY_RECEIVER = """
            SELECT email_id, sender.email AS sender, receiver.email AS receiver, subject, body, priority, sent_date
            FROM emails
            JOIN users sender
                ON sender.user_id = emails.sender_id
            JOIN users receiver
                ON receiver.user_id = emails.receiver_id
            WHERE deleted_by_receiver = FALSE AND folder_receiver IS NULL AND receiver_id =
            """;
    private static final String SQL_GET_EMAILS_BY_SENDER = """
            SELECT email_id, sender.email AS sender, receiver.email AS receiver, subject, body, priority, sent_date
            FROM emails
            JOIN users sender
                ON sender.user_id = emails.sender_id
            JOIN users receiver
                ON receiver.user_id = emails.receiver_id
            WHERE deleted_by_sender = FALSE AND sender_id =
            """;
    private static final String SQL_GET_TRASH_EMAILS_BY_USERID = """
            SELECT email_id, sender.email AS sender, receiver.email AS receiver, subject, body, priority, sent_date
            FROM emails
            JOIN users sender
                ON sender.user_id = emails.sender_id
            JOIN users receiver
                ON receiver.user_id = emails.receiver_id
            WHERE (receiver_id = %d AND deleted_by_receiver = TRUE) OR (sender_id = %d AND deleted_by_sender = TRUE)
            """;

    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserModel findUserById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new Object[]{userId}, userRowMapper);
    }

    @Override
    public List<EmailModel> getEmailsByReceiverId(Integer userId, Integer sort, Integer page) {
        String query = addSortingToQuery(SQL_GET_EMAILS_BY_RECEIVER + userId, sort);
        return jdbcTemplate.query(query,
                BeanPropertyRowMapper.newInstance(EmailModel.class));
    }

    @Override
    public List<EmailModel> getEmailsBySenderId(Integer userId, Integer sort, Integer page) {
        String query = addSortingToQuery(SQL_GET_EMAILS_BY_SENDER + userId, sort);
        return jdbcTemplate.query(query,
                BeanPropertyRowMapper.newInstance(EmailModel.class));
    }

    @Override
    public List<EmailModel> getTrashByUserId(Integer userId, Integer sort, Integer page) {
        String query = addSortingToQuery(String.format(SQL_GET_TRASH_EMAILS_BY_USERID, userId, userId), sort);
        return jdbcTemplate.query(query,
                BeanPropertyRowMapper.newInstance(EmailModel.class));
    }

    private String addSortingToQuery(String query, Integer sort) {
        return switch (sort) {
            case 0 -> query + " ORDER BY email_id DESC";
            case 1 -> query + " ORDER BY email_id ASC";
            case 2 -> query + " ORDER BY priority DESC";
            default -> query + " ORDER BY email_id DESC";
        };
    }

    private final RowMapper<UserModel> userRowMapper = ((rs, rowNum) -> new UserModel(
            rs.getInt("user_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
    ));
}
