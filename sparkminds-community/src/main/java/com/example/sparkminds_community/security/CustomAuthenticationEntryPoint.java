package com.example.sparkminds_community.security;

import com.example.sparkminds_community.constant.AuthConstant;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.utility.ResponsePayloadUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                AuthConstant.NOT_AUTHORIZE,
                HttpStatus.UNAUTHORIZED,
                null,
                AuthConstant.ACCESS_DENIED
        );
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(responsePayload));
    }
}
