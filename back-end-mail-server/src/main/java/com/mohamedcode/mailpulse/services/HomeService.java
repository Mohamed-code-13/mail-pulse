package com.mohamedcode.mailpulse.services;

import com.mohamedcode.mailpulse.models.UserModel;

import java.util.List;
import java.util.Map;

public interface HomeService {
    UserModel getUserById(Integer userId);

    List<Map<String, Object>> getInbox(Integer userId, Integer sort, Integer page);

    List<Map<String, Object>> getSent(Integer userId, Integer sort, Integer page);

    List<Map<String, Object>> getTrash(Integer userId, Integer sort, Integer page);
}
