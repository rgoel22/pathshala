package com.pathshala.service;

import com.pathshala.dto.UserModel;


public interface LoginService {

    UserModel userLogin(String userId, String password);
}
