package com.example.sparkminds_community.utility.impl;

import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.utility.ResponsePayloadUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponsePayloadUtilityImpl implements ResponsePayloadUtility {
    @Override
    public ResponsePayload buildResponse(String message, HttpStatus status, Object data, String error) {
        return ResponsePayload.builder()
                .message(message)
                .status(status)
                .data(data)
                .error(error)
                .build();
    }
}
