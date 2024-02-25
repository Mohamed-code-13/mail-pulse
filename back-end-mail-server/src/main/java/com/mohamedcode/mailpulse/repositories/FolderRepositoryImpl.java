package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FolderRepositoryImpl implements FolderRepository {

    private static final String SQL_GET_FOLDER_NAMES_PER_USER = "SELECT folder_name FROM folders WHERE user_id = ";
    private static final String SQL_COUNT_FOLDERS_BY_NAME = """
            SELECT COUNT(*)
            FROM folders
            WHERE user_id = ? AND folder_name = ?
            """;
    private static final String SQL_COUNT_EMAIL_BY_ID = """
            SELECT COUNT(*)
            FROM emails
            WHERE (sender_id = ? OR receiver_id = ?) AND email_id = ?
            """;
    private static final String SQL_INSERT_NEW_FOLDER = """
            INSERT INTO folders
                (user_id, folder_name)
            VALUES (?, ?)
            """;
    private static final String SQL_GET_EMAILS_PER_FOLDER = """
            SELECT
            	email_id,
            	sender.email AS sender,
            	receiver.email AS receiver,
            	subject,
            	body,
            	priority,
            	sent_date
            FROM emails
            JOIN users sender
            	ON sender.user_id = emails.sender_id
            JOIN users receiver
            	ON receiver.user_id = emails.receiver_id
            LEFT JOIN folders fold
            	ON folder_sender = fold.folder_id
            WHERE
                ((deleted_by_sender = FALSE AND sender_id = %d)
                 OR (deleted_by_receiver = FALSE AND receiver_id = %d))
                AND folder_receiver = %d
            """;
    private static final String SQL_MOVE_EMAIL_TO_FOLDER = """
            UPDATE emails
            SET folder_receiver = %d
            WHERE email_id = %d
            """;
    private static final String SQL_RESET_EMAIL_FOLDER = """
            UPDATE emails
            SET folder_receiver = NULL
            WHERE email_id = %d
            """;
    private static final String SQL_GET_FOLDER_ID_BY_NAME = "SELECT folder_id FROM folders WHERE user_id = ? AND folder_name = ?";
    private static final String SQL_DELETE_FOLDER = "DELETE FROM folders WHERE user_id = ? AND folder_name = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getFolderNames(Integer userId) {
        return jdbcTemplate.queryForList(SQL_GET_FOLDER_NAMES_PER_USER + userId.toString(),String.class);
    }

    @Override
    public void createFolder(Integer userId, String folderName) {
        jdbcTemplate.update(SQL_INSERT_NEW_FOLDER, userId, folderName);
    }

    @Override
    public Integer countFolderName(Integer userId, String folderName) {
        return jdbcTemplate.queryForObject(SQL_COUNT_FOLDERS_BY_NAME, new Object[]{userId, folderName}, Integer.class);
    }

    @Override
    public Integer countEmailId(Integer userId, Integer emailId) {
        return jdbcTemplate.queryForObject(SQL_COUNT_EMAIL_BY_ID, new Object[]{userId, userId, emailId}, Integer.class);
    }

    @Override
    public void deleteFolder(Integer userId, String folderName) {
        jdbcTemplate.update(SQL_DELETE_FOLDER, userId, folderName);
    }

    @Override
    public List<EmailModel> getEmailsByFolder(Integer userId, String folderName, Integer sort, Integer page) {
        Integer folderId = getFolderIdByName(userId, folderName);
        String query = addSortingToQuery(String.format(SQL_GET_EMAILS_PER_FOLDER, userId, userId, folderId), sort);
        return jdbcTemplate.query(query,
                BeanPropertyRowMapper.newInstance(EmailModel.class));
    }

    @Override
    public Integer getFolderIdByName(Integer userId, String folderName) {
        return jdbcTemplate.queryForObject(SQL_GET_FOLDER_ID_BY_NAME, new Object[]{userId, folderName}, Integer.class);
    }

    @Override
    public void moveEmail(Integer emailId, Integer folderId) {
        jdbcTemplate.update(String.format(SQL_MOVE_EMAIL_TO_FOLDER, folderId, emailId));
    }

    @Override
    public void resetEmailFolder(Integer emailId) {
        jdbcTemplate.update(String.format(SQL_RESET_EMAIL_FOLDER, emailId));
    }

    private String addSortingToQuery(String query, Integer sort) {
        return switch (sort) {
            case 0 -> query + " ORDER BY email_id DESC";
            case 1 -> query + " ORDER BY email_id ASC";
            case 2 -> query + " ORDER BY priority DESC";
            default -> query + " ORDER BY email_id DESC";
        };
    }
}
