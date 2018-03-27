package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.UserRepository;
import com.nelson.sign.entity.User;
import com.nelson.sign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User userLogin(String userName, String password) {
        return this.userRepository.getUserByNameAndPassword(userName,password);
    }

    @Override
    public User getUserByUid(Long uid) {
        return null;
    }

    @Override
    public Boolean deleteUserByUid(Long uid) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.saveAndFlush(user);
    }
}
