package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.EmailModel;

import java.util.List;

public interface FolderRepository {
    List<String> getFolderNames(Integer userId);

    void createFolder(Integer userId, String folderName);

    void deleteFolder(Integer userId, String folderName);

    List<EmailModel> getEmailsByFolder(Integer userId, String folderName, Integer sort, Integer page);
}
