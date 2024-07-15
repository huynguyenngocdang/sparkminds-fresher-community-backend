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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                AuthConstant.NOT_ALLOWED,
                HttpStatus.UNAUTHORIZED,
                null,
                AuthConstant.ACCESS_DENIED
        );
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(responsePayload));
    }
}
