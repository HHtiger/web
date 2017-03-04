package com.tiger.controller;

import com.tiger.model.User;
import com.tiger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tiger on 2017/3/4.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/query", method = {RequestMethod.POST,RequestMethod.GET})
    public String insert(Model model) {
        User u = userService.selectByPrimaryKey(1);
        model.addAttribute("obj",u);
        return "showUser";
    }

}
