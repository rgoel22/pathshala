package com.pathshala.controller;

import com.pathshala.dto.UserDTO;
import com.pathshala.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private UserService userService;
    @PostMapping()
    public ResponseEntity<Boolean> SignUp(@RequestBody @Valid UserDTO userDTO){
        Boolean signUpBool = userService.saveUserData(userDTO);
        return ResponseEntity.ok().body(signUpBool);
    }

}
