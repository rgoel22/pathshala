package com.pathshala.security;

import com.pathshala.config.PropertyConfig;
import com.pathshala.util.AESUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class Token {

    private PropertyConfig config;
    public String createToken(String userId, String userRole) {
        long now = new Date().getTime();
        String plainToken = now + "#" + userId + "#" + userRole + "#" + now;
        return AESUtility.doEncrypt(plainToken, config.getPropertyByName("secretKey"));
    }

    public String validateToken(String userId, String token) {
        return null;
    }


}
