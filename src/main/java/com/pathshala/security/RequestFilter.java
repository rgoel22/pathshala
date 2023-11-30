package com.pathshala.security;

import com.pathshala.exception.BaseRuntimeException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException, BaseRuntimeException {
        System.out.println("hello");
        filterChain.doFilter(request, response);
    }

}
