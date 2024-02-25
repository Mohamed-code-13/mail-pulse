package com.mohamedcode.mailpulse.services;

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
}
