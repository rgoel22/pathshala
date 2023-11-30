package com.pathshala.security;

import com.pathshala.exception.BaseRuntimeException;
import com.pathshala.exception.UnauthorizedAccessException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RequestFilter implements Filter {

    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException, BaseRuntimeException {
        tokenService.expireToken();
        String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject jsonObject = new JSONObject(payload);
        Long userId = jsonObject.optLongObject("userId", 0L);
        String token = jsonObject.optString("token", "");
        String userType = jsonObject.optString("userType", "");
        if (true || tokenService.validateToken(userId, token, userType)) {
            filterChain.doFilter(request, response);
        } else {
            throw new UnauthorizedAccessException("", "");
        }
    }

}
