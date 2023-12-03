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
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private TokenService tokenService;
    private SessionInfoService sessionInfoService;
    private final ModelMapper modelMapper;
    private UserCourseMappingService userCourseMappingService;
    private CourseService courseService;

    public Boolean saveUserData(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        //check if user is already present using the userId
        Optional<UserEntity> userEntityOptional = userRepository.findByUserIdAndIsActiveTrue(userDTO.getUserId());
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
        Optional<UserEntity> optionalUser = userRepository.findByUserIdAndPasswordAndIsActiveTrue(payload.getUserId(), hashedPassword);
        if(optionalUser.isEmpty()){
            throw new GenericExceptions(ErrorCodes.INCORRECT_CREDENTIALS, "Incorrect credentials!");
        }
        UserEntity user = optionalUser.get();
        String token = tokenService.createToken(user.getId(), user.getUserType().toString());
        if (sessionInfoService.createSession(user.getId(), token)) {
            UserDTO validUser = new UserDTO();
            if (user.getUserType().equals(UserType.STUDENT)){
                validUser = getStudentDetails(user.getId());
            } else if (user.getUserType().equals(UserType.INSTRUCTOR)) {
                validUser = getInstructorDetails(user.getId());
            } else if(user.getUserType().equals(UserType.ADMIN)) {
                validUser = getAdminDetails(user.getId());
            }
            return LoginRequestDTO.builder().userId(user.getId().toString())
                    .userType(user.getUserType().toString())
                    .token(token).userDetails(validUser).build();
        }
        throw new BaseRuntimeException("","");
    }

    private UserDTO getAdminDetails(Long userId) {
        UserEntity user = this.findEntityById(userId);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    private UserDTO getInstructorDetails(Long userId) {
        UserEntity user = this.findEntityById(userId);
        List<CourseDTO> courses = courseService.instructorCourse(userId);
        for(CourseDTO course: courses){
            course.setUserId(null);
            course.setUserType(null);
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        userDTO.setCourses(courses);
        return userDTO;
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

    public List<UserDTO> getInstructor() {
        return getListOfUser(UserType.INSTRUCTOR);
    }

    private List<UserDTO> getListOfUser(UserType userType){
        List<UserEntity> users = userRepository.findAllByUserTypeAndIsActiveTrue(userType);
        List<UserDTO> userDTOS = users.stream().map(instructor -> modelMapper.map(instructor, UserDTO.class))
                .collect(Collectors.toList());
        for (UserDTO user: userDTOS) {
            user.setPassword(null);
            user.setCourses(null);
        }
        return userDTOS;
    }

    public UserDTO updateUser(UserDTO payload) {
        UserEntity savedUser = findEntityById(payload.getId());
        payload.setPassword(savedUser.getPassword());
        payload.setUserType(savedUser.getUserType());
        payload.setUserId(savedUser.getUserId());
        modelMapper.map(payload, savedUser);
        UserEntity updatedUser = userRepository.save(savedUser);
        UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);
        userDTO.setPassword(null);
        userDTO.setCourses(null);
        userDTO.setUserType(null);
        return userDTO;
    }

    public List<UserDTO> getStudent() {
        return getListOfUser(UserType.STUDENT);
    }

    @Transactional
    public Boolean deleteUser(Long userId) {
        int noOfRecords = userRepository.markUserInActive(userId);
        return noOfRecords == 1;
    }
}
