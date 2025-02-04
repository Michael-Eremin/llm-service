package com.example.llm_service.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class LLMServiceImpl implements LLMService {
    private final ChatClient chatClient;
    @Value("${feature.save-to-mongodb-enabled}")
    private boolean saveToMongodbEnabled;
    private final Logger LOGGER = LoggerFactory.getLogger("LLMService");



    public LLMServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String askQuestion(String question) {
        String response = chatClient.prompt()
                .user(question)
                .call()
                .content();
        if (saveToMongodbEnabled) {
            saveArchive(question, response);
        }


        return response;
    }

}
