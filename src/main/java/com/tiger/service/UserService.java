package com.tiger.service;

import com.tiger.model.User;

/**
 * Package: com.tiger.service
 * ClassName: UserSerice
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016-03-29
 * Version: 1.0
 */
public interface UserService {

    User selectByPrimaryKey(Integer id);

}
