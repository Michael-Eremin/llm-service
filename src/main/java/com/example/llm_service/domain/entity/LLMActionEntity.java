package com.example.llm_service.domain.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;



@Document(collection = "llm_action")
@Data
public class LLMActionEntity {
    @Id
    private String id;
    private String question;
    private String response;
    private LocalDateTime timestamp;
    private int statusCode;
    private int tokensUsed;
}
