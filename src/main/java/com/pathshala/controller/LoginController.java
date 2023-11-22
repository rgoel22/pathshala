package com.pathshala.controller;

import com.pathshala.dao.LoginRequestModel;
import com.pathshala.dto.UserModel;
import com.pathshala.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(name = "/{id}", produces = "application/json")
    public UserModel userLogin(LoginRequestModel loginRequestModel){
        //serviceMethod call
        UserModel userModel = loginService.userLogin(loginRequestModel.getUserId(), loginRequestModel.getPassword());
        return userModel;
    }
}