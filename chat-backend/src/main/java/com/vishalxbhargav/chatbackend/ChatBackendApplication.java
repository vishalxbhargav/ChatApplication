package com.vishalxbhargav.chatbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ChatBackendApplication{
	public static void main(String[] args) {
		SpringApplication.run(ChatBackendApplication.class, args);
	}
}
