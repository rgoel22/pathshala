package com.pathshala.controller;

import com.pathshala.dto.LoginRequestDTO;
import com.pathshala.dto.UserDTO;
import com.pathshala.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginRequestDTO loggedInUser = userService.login(loginRequestDTO);
        return ResponseEntity.ok().body(loggedInUser.getUserId());
    }

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> SignUp(@RequestBody @Valid UserDTO userDTO) {
        Boolean signUpBool = userService.saveUserData(userDTO);
        return ResponseEntity.ok().body(signUpBool);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestParam(required = true) Long userId) {
        Boolean isLoggedOut = userService.logout(userId);
        return ResponseEntity.ok().build();
    }
}
