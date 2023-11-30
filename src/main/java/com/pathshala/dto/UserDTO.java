package com.pathshala.dto;

import com.pathshala.enums.UserType;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private String userId;
    private String password;
    private String rePassword;

}
