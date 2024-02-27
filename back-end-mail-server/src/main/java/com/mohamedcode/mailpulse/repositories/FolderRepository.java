package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.EmailModel;

import java.util.List;

public interface FolderRepository {
    List<String> getFolderNames(Integer userId);

    void createFolder(Integer userId, String folderName);

    Integer countFolderName(Integer userId, String folderName);

    Integer countEmailId(Integer userId, Integer emailId);

    void deleteFolder(Integer userId, String folderName);

    List<EmailModel> getEmailsByFolder(Integer userId, String folderName, Integer sort, Integer page);

    Integer getFolderIdByName(Integer userId, String folderName);

    void moveEmail(Integer emailId, Integer folderId);

    void resetEmailFolder(Integer emailId);

}
