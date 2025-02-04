package com.example.llm_service.infrastructure.repositories;

import com.example.llm_service.domain.entity.LLMActionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LLMActionRepository extends MongoRepository<LLMActionEntity, String> {
}
