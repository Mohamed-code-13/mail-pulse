package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Override
    public void deleteEmail(Integer emailID) {
        // delete email from the current folder
        // add the deleted email to the trash folder
        // if the email in the trash folder delete it from the db
        emailRepository.deleteEmail(emailID);
    }

    @Override
    public void sendEmail(Integer senderId, List<String> receivers,
                          String subject, String body, Integer priority,
                          List<MultipartFile> files) {
        // create a new email and add it to the db
        emailRepository.sendEmail(senderId, receivers,
                               subject, body, priority, files);
    }
}
