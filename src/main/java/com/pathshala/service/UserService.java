package com.pathshala.service;

import com.pathshala.dao.UserEntity;
import com.pathshala.dto.LoginRequestDTO;
import com.pathshala.dto.UserDTO;
import com.pathshala.exception.ErrorCodes;
import com.pathshala.exception.RecordExistsException;
import com.pathshala.repository.GenericExceptions;
import com.pathshala.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    public Boolean saveUserData(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        //check if user is already present using the userId
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userDTO.getUserId());
        if(userEntityOptional.isPresent()){
            throw new RecordExistsException(ErrorCodes.USER_ALREADY_PRESENT, "UserId not available!");
        }
        // check if passwords match
        if(userDTO.getPassword().equals(userDTO.getRePassword())){
            throw new GenericExceptions(ErrorCodes.PASSWORD_MISMATCH, "Passwords do not Match");
        }
        //emailId should not be empty
        if(Objects.isNull(userDTO.getEmail())){
            throw new GenericExceptions(ErrorCodes.MISSING_EMAIL, "Email is required.");
        }
        // encrypt the password using hashing
        String hashedPassword = String.valueOf(Arrays.hashCode(userDTO.getPassword().toCharArray()));
        userDTO.setPassword(hashedPassword);
        //map object to database entity
        modelMapper.map(userDTO, user);
        try{
            userRepository.save(user);
        } catch (Exception e){
            throw new GenericExceptions(ErrorCodes.DATA_NOT_SAVED, "User data not saved. Please try again!");
        }
        return true;
    }

    public LoginRequestDTO login(String userId, String password){
        LoginRequestDTO loginInfo = new LoginRequestDTO();
        String hashedPassword = String.valueOf(Arrays.hashCode(password.toCharArray()));
        Optional<UserEntity> optionalUser = userRepository.findByUserIdAndPassword(userId, hashedPassword);
        if(optionalUser.isEmpty()){
            throw new GenericExceptions(ErrorCodes.INCORRECT_CREDENTIALS, "Incorrect credentials!");
        }
        loginInfo.setUserId(userId);
        loginInfo.setPassword(password);
        loginInfo.setUserIdType(optionalUser.get().getUserId());
        return loginInfo;
    }
}
