package com.vishalxbhargav.chatbackend.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorDetails(String message, String description, LocalDateTime now) {
    }
}
