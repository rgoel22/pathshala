package com.pathshala.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    private String userId;
    private String password;

    private String userIdType;
}
