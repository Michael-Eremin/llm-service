package com.example.llm_service.domain.entity;


import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "answers")
@Data
public class AnswerEntity {
//    @Id
    private String id;
    private String question;
    private String response;

    public AnswerEntity() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {}
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {}
}
