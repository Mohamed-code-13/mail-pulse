package com.mohamedcode.mailpulse.services;

import java.util.List;
import java.util.Map;

public interface FolderService {

    List<String> getFolderNames(Integer userId);

    void createFolder(Integer userId, String folderName);

    void deleteFolder(Integer userId, String folderName);

    List<Map<String, Object>> getEmailsByFolder(Integer userId, String folderName, Integer sort, Integer page);
}
