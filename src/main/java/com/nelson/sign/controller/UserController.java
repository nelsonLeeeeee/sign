package com.nelson.sign.controller;

import com.nelson.sign.entity.User;
import com.nelson.sign.service.UserService;
import com.nelson.sign.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin")
    public Result userLogin(@RequestParam("name") String name,
                           @RequestParam("password") String password){
       User user =  this.userService.userLogin(name,password);
       Result result= new Result("OK");
       if(user==null){
        result.status="账号或密码错误";
       }else{
           user.setLastLoginTime(System.currentTimeMillis());
           user = this.userService.addUser(user);
       }
       result.resultMap.put("user",user);
       return result;
    }

    @RequestMapping(value = "/addUser")
    public Result addUser(@Valid User user){
        user.setIsused(1);
        user = userService.addUser(user);
        Result result= new Result("OK");
        result.resultMap.put("user",user);
        return result;
    }

}
