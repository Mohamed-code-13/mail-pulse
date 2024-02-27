package com.mohamedcode.mailpulse.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmailService {
    void deleteEmail(Integer userId, List<Integer> email_ids);
    void restoreEmail(Integer userId, List<Integer> email_ids);

    void sendEmail(Integer senderId, List<String> receivers,
                   String subject, String body, Integer priority,
                   List<MultipartFile> files);
}
