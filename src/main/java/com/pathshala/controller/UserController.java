package com.pathshala.controller;

import com.pathshala.dto.LoginRequestDTO;
import com.pathshala.dto.UserDTO;
import com.pathshala.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "https://pathshala-api-8e4271465a87.herokuapp.com", maxAge = 360000)
public class UserController {
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginRequestDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginRequestDTO loggedInUser = userService.login(loginRequestDTO);
        return ResponseEntity.ok().body(loggedInUser);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody @Valid UserDTO userDTO) {
        Boolean signUpBool = userService.saveUserData(userDTO);
        return ResponseEntity.ok().body(signUpBool);
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestParam Long userId) {
        Boolean isLoggedOut = userService.logout(userId);
        return ResponseEntity.ok().body(isLoggedOut);
    }

    @GetMapping("/getInstructor")
    public ResponseEntity<List<UserDTO>> getInstructor(){
        List<UserDTO> instructors = userService.getInstructor();
        return ResponseEntity.ok().body(instructors);
    }
}

