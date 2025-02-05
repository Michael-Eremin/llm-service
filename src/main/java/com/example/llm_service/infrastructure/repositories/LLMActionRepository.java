package com.example.llm_service.infrastructure.repositories;

import com.example.llm_service.domain.entity.LLMActionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LLMActionRepository extends MongoRepository<LLMActionEntity, String> {
}
