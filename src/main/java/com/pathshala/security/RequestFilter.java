package com.pathshala.security;

import com.pathshala.exception.BaseRuntimeException;
import com.pathshala.exception.UnauthorizedAccessException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.RequestFacade;
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
//        if(((RequestFacade) request).getMethod().equalsIgnoreCase("OPTIONS")){
//            response.setContentType("text/plain");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write("OK");
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");
//            response.getWriter().flush();
//            response.getWriter().close();
//
//        } else{
            String url = ((RequestFacade) request).getRequestURL().toString();
            tokenService.expireToken();
            if(false){
           // if( !url.contains("login") && !url.contains("signUp")){
                String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                JSONObject jsonObject = new JSONObject(payload);
                Long userId = jsonObject.optLongObject("loogedInUserId", 0L);
                String token = jsonObject.optString("token", "");
                String userType = jsonObject.optString("userType", "");
                if (tokenService.validateToken(userId, token, userType)) {
                    filterChain.doFilter(request, response);
                } else {
                    throw new UnauthorizedAccessException("Hello", "Hello");
                }
            } else{

                ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
                ((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");

                filterChain.doFilter(request, response);
            }

    }

}
