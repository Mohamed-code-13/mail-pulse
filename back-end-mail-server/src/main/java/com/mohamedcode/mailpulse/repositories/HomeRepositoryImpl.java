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
            SELECT email_id, sender.email AS sender, receiver.email AS receiver, subject, description, priority
            FROM emails
            JOIN users sender
                ON sender.user_id = emails.sender_id
            JOIN users receiver
                ON receiver.user_id = emails.receiver_id
            WHERE receiver_id = 
            """;

    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserModel findUserById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new Object[]{userId}, userRowMapper);
    }

    @Override
    public List<EmailModel> getEmailsByReceiverId(Integer userId) {
        return jdbcTemplate.query(SQL_GET_EMAILS_BY_RECEIVER + userId,
                BeanPropertyRowMapper.newInstance(EmailModel.class));
    }

    private final RowMapper<UserModel> userRowMapper = ((rs, rowNum) -> new UserModel(
            rs.getInt("user_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
    ));
}
