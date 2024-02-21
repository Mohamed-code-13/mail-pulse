package com.mohamedcode.mailpulse.repositories;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmailRepository {
    void deleteEmail(Integer emailID);

    void sendEmail(Integer senderId, List<String> receivers,
                   String subject, String body, Integer priority,
                   List<MultipartFile> files);
}
