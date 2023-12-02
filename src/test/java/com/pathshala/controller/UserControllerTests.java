package com.pathshala.controller;

import com.pathshala.dto.LoginRequestDTO;
import com.pathshala.dto.UserDTO;
import com.pathshala.enums.UserType;
import com.pathshala.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
@Disabled
public class UserControllerTests {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setup(){
        userController = new UserController(userService);
    }

    @AfterEach
    void tearDown(){
        userController = null;
    }

    @Test
//    @Disabled
    void testLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Mockito.when(userService.login(Mockito.any())).thenReturn(loginRequestDTO());
        ResponseEntity<LoginRequestDTO> testLoginResponse = userController.login(loginRequestDTO());
        Assertions.assertTrue(testLoginResponse.hasBody());
        Assertions.assertEquals(testLoginResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(testLoginResponse.getBody()).getUserId(), "TestUser");
    }

    @Test
    void testSignUp(){
        Mockito.when(userService.saveUserData(Mockito.any())).thenReturn(true);
        ResponseEntity<Boolean> testSignUpResponse = userController.signUp(signUpUserDTO());
        Assertions.assertTrue(testSignUpResponse.hasBody());
        Assertions.assertEquals(testSignUpResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(testSignUpResponse.getBody(), true);
    }

    @Test
    void testLogOut(){
        Mockito.when(userService.logout(Mockito.any())).thenReturn(true);
        ResponseEntity testLogoutResponse = userController.logout(1L);
        Assertions.assertEquals(testLogoutResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testHome(){
        ResponseEntity<String> responseString = userController.hello();
        Assertions.assertTrue(responseString.hasBody());
        Assertions.assertEquals(responseString.getBody(), "Hello");
    }

    private LoginRequestDTO loginRequestDTO(){
        return new LoginRequestDTO("TestUser", "testPass","testToken", UserType.ADMIN.toString());

    }

    private UserDTO signUpUserDTO(){
        return new UserDTO("fname", "lname", "test@test.com", "99887766", UserType.STUDENT, "testId", "pass","pass");
    }
}
