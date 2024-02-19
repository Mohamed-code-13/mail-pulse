package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.models.UserModel;

import java.util.List;
import java.util.Map;

public interface HomeService {
    UserModel getUserById(Integer userId);

    List<Map<String, Object>> getInbox(Integer userId);

}
