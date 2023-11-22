package com.pathshala.service;

import com.pathshala.Mapper.UserMapper;
import com.pathshala.dao.UserEntity;
import com.pathshala.dto.UserModel;
import com.pathshala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserModel userLogin(String userId, String password) {
        Optional<UserEntity> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isEmpty()){
            return null;
        }
        String userPassword = optionalUser.get().getPassword();
        if(userPassword.isEmpty()){
            return null;//some error
        }
        if(!userPassword.equals(password)){
            return null;//some error
        }
        return userMapper.mapUserEntityToUserModel(optionalUser.get());
    }
}
