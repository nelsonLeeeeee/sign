package com.nelson.sign.controller;

import com.nelson.sign.entity.User;
import com.nelson.sign.enums.ResultEnum;
import com.nelson.sign.handle.SignException;
import com.nelson.sign.service.UserService;
import com.nelson.sign.utils.Result;
import com.nelson.sign.utils.ResultUtil;
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
    public Result<User> userLogin(@RequestParam("name") String name,
                           @RequestParam("password") String password){
       User user =  this.userService.userLogin(name,password);

       if(user==null){
           throw new SignException(ResultEnum.ERR_COUNT_PASSWORD);
       }else{
           user.setLastLoginTime(System.currentTimeMillis());
           user = this.userService.addUser(user);
       }
       return ResultUtil.success(user);
    }

    @RequestMapping(value = "/addUser")
    public Result<User> addUser(@Valid User user){
        user.setIsused(1);
        user = userService.addUser(user);
        return ResultUtil.success(user);
    }

}
