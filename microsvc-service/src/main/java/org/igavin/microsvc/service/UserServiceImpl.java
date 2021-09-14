package org.igavin.microsvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.igavin.microsvc.dao.mapper.UserMapper;
import org.igavin.microsvc.api.UserService;
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public String getTopOne() {
        String topOne = userMapper.getTopOne();
        return topOne;
    }

}
