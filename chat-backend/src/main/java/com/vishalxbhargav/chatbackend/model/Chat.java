package com.vishalxbhargav.chatbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String chat_name;
    private String chat_image;
    @Column(name="is_Group")
    private boolean isGroup;
    @JoinColumn(name="created_by")
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private Set<User> users=new HashSet<>();
    @ManyToMany
    private Set<User> admins=new HashSet<>();
    @OneToMany
    private List<Message> messages= new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat chat)) return false;
        return isGroup == chat.isGroup && Objects.equals(id, chat.id) && Objects.equals(chat_name, chat.chat_name) && Objects.equals(chat_image, chat.chat_image) && Objects.equals(createdBy, chat.createdBy) && Objects.equals(users, chat.users) && Objects.equals(messages, chat.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat_name, chat_image, isGroup, createdBy, users, messages);
    }
}
