package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.exceptions.CustomAuthException;
import com.mohamedcode.mailpulse.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FolderServiceImpl implements FolderService {

    @Autowired
    FolderRepository folderRepository;

    @Override
    public List<String> getFolderNames(Integer userId) {
        return folderRepository.getFolderNames(userId);
    }

    @Override
    public void createFolder(Integer userId, String folderName) {
        int count = folderRepository.countFolderName(userId, folderName);
        if (count > 0)
            throw new CustomAuthException("Folder name already exists!");
        folderRepository.createFolder(userId, folderName);
    }

    @Override
    public void deleteFolder(Integer userId, String folderName) {
        folderRepository.deleteFolder(userId, folderName);
    }

    @Override
    public List<Map<String, Object>> getEmailsByFolder(Integer userId, String folderName, Integer sort, Integer page) {
        var emails = folderRepository.getEmailsByFolder(userId, folderName, sort, page);
        List<Map<String, Object>> res = new ArrayList<>();

        for (var email : emails)
            res.add(email.toMap());

        return res;
    }

    @Override
    public void moveEmails(Integer userId, List<Integer> emailIds, String folderName) {
        for (Integer id : emailIds) {
            moveEmail(userId, id, folderName);
        }
    }

    private void moveEmail(Integer userId, Integer emailId, String folderName) {
        if (folderName.equals("inbox")) {
            folderRepository.resetEmailFolder(emailId);
            return;
        }

        int folderCount = folderRepository.countFolderName(userId, folderName);
        int emailCount = folderRepository.countEmailId(userId, emailId);
        if (folderCount < 1) {
            throw new CustomAuthException("No such folder with this name!");
        } else if (emailCount < 1) {
            throw new CustomAuthException("No such email with this id!");
        }

        Integer folderId = folderRepository.getFolderIdByName(userId, folderName);

        folderRepository.moveEmail(emailId, folderId);
    }
}
