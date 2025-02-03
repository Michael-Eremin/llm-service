package com.example.llm_service.services;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;


@Service
public class LLMServiceImpl implements LLMService {
    private final ChatClient chatClient;

    public LLMServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String askQuestion(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
