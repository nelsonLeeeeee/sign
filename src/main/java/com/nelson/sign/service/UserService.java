package com.nelson.sign.service;

import com.nelson.sign.entity.User;

public interface UserService {
    User userLogin(String userName,String password);

    User getUserByUid(Long uid);

    Boolean deleteUserByUid(Long uid);

    User addUser(User user);
}
