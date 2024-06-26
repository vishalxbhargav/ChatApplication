package com.vishalxbhargav.chatbackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatRequest {
    private List<Integer> users;
    private String chat_name;
    private String chat_image;
}
