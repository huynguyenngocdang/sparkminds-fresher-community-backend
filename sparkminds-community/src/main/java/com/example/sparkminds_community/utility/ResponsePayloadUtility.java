package com.example.sparkminds_community.utility;

import com.example.sparkminds_community.payload.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public interface ResponsePayloadUtility {
    ResponsePayload buildResponse(String message, HttpStatus status, Object data, String error);
}
