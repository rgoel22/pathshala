package com.pathshala.service;

import com.pathshala.dao.UserEntity;
import com.pathshala.dto.CourseDTO;
import com.pathshala.dto.LoginRequestDTO;
import com.pathshala.dto.UserDTO;
import com.pathshala.enums.UserType;
import com.pathshala.exception.BaseRuntimeException;
import com.pathshala.exception.ErrorCodes;
import com.pathshala.exception.GenericExceptions;
import com.pathshala.exception.NotFoundException;
import com.pathshala.exception.RecordExistsException;
import com.pathshala.repository.UserRepository;
import com.pathshala.security.TokenService;
import com.pathshala.util.EncryptionUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private TokenService tokenService;
    private SessionInfoService sessionInfoService;
    private final ModelMapper modelMapper;
    private UserCourseMappingService userCourseMappingService;

    public Boolean saveUserData(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        //check if user is already present using the userId
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userDTO.getUserId());
        if(userEntityOptional.isPresent()){
            throw new RecordExistsException(ErrorCodes.USER_ALREADY_PRESENT, "UserId not available!");
        }
        // check if passwords match
        if(!userDTO.getPassword().equals(userDTO.getRePassword())){
            throw new GenericExceptions(ErrorCodes.PASSWORD_MISMATCH, "Passwords do not Match");
        }
        //emailId should not be empty
        if(Objects.isNull(userDTO.getEmailId())){
            throw new GenericExceptions(ErrorCodes.MISSING_EMAIL, "Email is required.");
        }
        // encrypt the password using hashing
        try{
            String hashedPassword = EncryptionUtility.makeSHA1Hash(userDTO.getPassword());
            userDTO.setPassword(hashedPassword);
            modelMapper.map(userDTO, user);
            userRepository.save(user);
        } catch (Exception e){
            throw new GenericExceptions(ErrorCodes.DATA_NOT_SAVED, "User data not saved. Please try again!");
        }
        //map object to database entity
        return true;
    }

    public LoginRequestDTO login(LoginRequestDTO payload) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hashedPassword = EncryptionUtility.makeSHA1Hash(payload.getPassword());
        Optional<UserEntity> optionalUser = userRepository.findByUserIdAndPassword(payload.getUserId(), hashedPassword);
        if(optionalUser.isEmpty()){
            throw new GenericExceptions(ErrorCodes.INCORRECT_CREDENTIALS, "Incorrect credentials!");
        }
        UserEntity user = optionalUser.get();
        String token = tokenService.createToken(user.getId(), user.getUserType().toString());
        if (sessionInfoService.createSession(user.getId(), token)){
            if (user.getUserType().equals(UserType.STUDENT.toString())){

            } else if (user.getUserType().equals(UserType.INSTRUCTOR.toString())) {

            }
            return LoginRequestDTO.builder().userId(user.getId().toString())
                    .userType(user.getUserType().toString())
                    .token(token).build();
        }
        throw new BaseRuntimeException("","");
    }

    public Boolean logout(Long userId) {
        return sessionInfoService.expireSessionForUserId(userId);
    }

    public UserDTO getStudentDetails(Long userId){
        UserEntity user = this.findEntityById(userId);
        List<CourseDTO> courses = userCourseMappingService.enrolledStudentCourses(userId);
        for(CourseDTO course: courses){
            course.setUserId(null);
            course.setUserType(null);
            course.setId(null);
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        userDTO.setCourses(courses);
        return userDTO;
    }

    private UserEntity findEntityById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException(ErrorCodes.USER_NOT_FOUND, "User not found!");
        }
        return user.get();
    }

}
