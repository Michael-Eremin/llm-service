package com.example.llm_service.infrastructure.config;

//import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ChatConfig {
    @Value("${spring.ai.llm.model}") String model;

    @Bean
    public ChatClient chatClient(LLMChatModel llmChatModel) {
        return ChatClient.builder(llmChatModel)
                .defaultSystem(model)
                .build();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }



}
