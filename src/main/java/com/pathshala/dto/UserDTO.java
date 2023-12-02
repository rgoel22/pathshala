package com.pathshala.dto;

import com.pathshala.enums.UserType;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private UserType userType;
    private String userId;
    private String password;
    private String rePassword;
    private List<CourseDTO> courses;
}
