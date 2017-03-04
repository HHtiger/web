package com.tiger.service.impl;

import com.tiger.dao.UserMapper;
import com.tiger.model.User;
import com.tiger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.tiger.service.impl
 * ClassName: UserServiceImpl
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016-03-29
 * Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper UserMapper;

    @Override
    public User selectByPrimaryKey(Integer id) {
        return UserMapper.selectByPrimaryKey(id);
    }
}
