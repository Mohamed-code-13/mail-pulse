package com.mohamedcode.mailpulse.repositories;

import com.mohamedcode.mailpulse.models.EmailModel;
import com.mohamedcode.mailpulse.models.UserModel;

import java.util.List;

public interface HomeRepository {

    UserModel findUserById(Integer userId);

    List<EmailModel> getEmailsByReceiverId(Integer userId);
}
