package com.roy.controller;

import com.roy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "hello World!";
    }

    @RequestMapping("/findUser")
    @ResponseBody
    String findUser(@RequestParam("userName") String userName){
        return userService.findByUserName(userName).getPassword();
    }

    @RequestMapping("/regUser")
    @ResponseBody
    boolean regUser(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd){
        userService.regUser(userName,pwd);
        return true;
    }
}
