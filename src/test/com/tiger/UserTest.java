package com.tiger;

import com.tiger.model.User;
import com.tiger.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Package: com.tiger.dao
 * ClassName: UserMapperTest
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016-03-29
 * Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserTest {
    private static Logger log = LogManager.getRootLogger();
    @Autowired
    UserService userService;

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void selectByPrimaryKey() throws Exception {
        User u = userService.selectByPrimaryKey(3);
        log.info(u.getUsername());
    }
}