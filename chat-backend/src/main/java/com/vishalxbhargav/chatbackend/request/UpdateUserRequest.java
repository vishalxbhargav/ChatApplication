package com.vishalxbhargav.chatbackend.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String full_name;
    private String profile_picture;
}
