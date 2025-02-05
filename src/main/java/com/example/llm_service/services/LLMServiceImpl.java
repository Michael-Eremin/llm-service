package com.example.llm_service.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class LLMServiceImpl implements LLMService {
    private final ChatClient chatClient;
    @Value("${feature.save-to-mongodb-enabled}")
    private boolean saveToMongodbEnabled;
    private ArchiveService archiveService;
    private final Logger LOGGER = LoggerFactory.getLogger("LLMService");



    public LLMServiceImpl(
            @Autowired ChatClient chatClient,
            @Autowired ArchiveService archiveService) {
        this.chatClient = chatClient;
        this.archiveService = archiveService;
    }

    public String askQuestion(String question) {
        ChatResponse response = chatClient.prompt()
                .user(question)
                .call()
                .chatResponse();

        String textResp = response.getResult().getOutput().getText();
        LOGGER.info("textResp " + textResp);
        Integer totalTokens = (Integer) response.getResult().getOutput().getMetadata().get("tokens");
        LOGGER.info("totalTokens " + totalTokens);
        Integer httpStatus = (Integer) response.getResult().getOutput().getMetadata().get("statusCode");
        LOGGER.info("httpStatus " + httpStatus);


        if (saveToMongodbEnabled) {
            archiveService.saveArchive(question, textResp, totalTokens, httpStatus);
        }


        return textResp;
    }

}
