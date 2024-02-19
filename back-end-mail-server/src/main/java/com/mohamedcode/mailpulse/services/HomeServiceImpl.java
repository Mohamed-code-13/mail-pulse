package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.models.EmailModel;
import com.mohamedcode.mailpulse.models.UserModel;
import com.mohamedcode.mailpulse.repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeRepository homeRepository;

    @Override
    public UserModel getUserById(Integer userId) {
        return homeRepository.findUserById(userId);
    }

    @Override
    public List<Map<String, Object>> getInbox(Integer userId) {
        var emails = homeRepository.getEmailsByReceiverId(userId);
        List<Map<String, Object>> res = new ArrayList<>();

        for (var email : emails)
            res.add(convertEmailToMap(email));

        return res;
    }

    private Map<String, Object> convertEmailToMap(EmailModel emailModel) {
        Map<String, Object> map = new HashMap<>();

        map.put("email_id", emailModel.getEmailId());
        map.put("sender", emailModel.getSender());
        map.put("receiver", emailModel.getReceiver());
        map.put("subject", emailModel.getSubject());
        map.put("description", emailModel.getDescription());
        map.put("priority", emailModel.getPriority());

        return map;
    }
}
