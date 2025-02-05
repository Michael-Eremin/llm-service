package com.example.llm_service.services;

import com.example.llm_service.domain.entity.LLMActionEntity;
import com.example.llm_service.infrastructure.repositories.LLMActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    LLMActionRepository llmActionRepository;
    private final Logger LOGGER = LoggerFactory.getLogger("ArchiveService");

    public ArchiveServiceImpl(
            @Autowired LLMActionRepository llmActionRepository) {
        this.llmActionRepository = llmActionRepository;
    }

    @Override
    public void saveArchive(String question, String response, Integer tokens, Integer statusCode){


        LLMActionEntity llmActionEntity = new LLMActionEntity();

        llmActionEntity.setQuestion(question);
        llmActionEntity.setResponse(response);
        llmActionEntity.setTimestamp(LocalDateTime.now());
        llmActionEntity.setStatusCode(statusCode);
        llmActionEntity.setTokensUsed(tokens);

        LLMActionEntity llmAction  = llmActionRepository.save(llmActionEntity);
        LOGGER.info(llmAction.toString());

    }

}
