package com.example.llm_service.infrastructure.config;

import com.example.llm_service.infrastructure.repositories.LLMActionRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ConditionalOnProperty(name = "feature.save-to-mongodb-enabled", havingValue = "true")
@EnableMongoRepositories(basePackageClasses = LLMActionRepository.class)
public class MongoConfig {
}
