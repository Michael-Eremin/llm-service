package com.example.llm_service.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@Getter
@NoArgsConstructor
public class LLMChatRequest {
    private List<Message> messages;
    private String model;
    private int max_tokens;
    private double temperature;
    private double top_p;
    private boolean stream;

//    public LLMChatRequest(List<Message> messages, String model, int max_tokens, double temperature, double top_p, boolean stream) {
//        this.messages = messages;
//        this.model = model;
//        this.max_tokens = max_tokens;
//        this.temperature = temperature;
//        this.top_p = top_p;
//        this.stream = stream;
//    }

//    public LLMChatRequest() {}
//
//    public void setMessages(List<Message> messages) {}
//    public List<Message> getMessages() {
//        return messages;
//    }
//
//
//    public void setModel(String model) {}
//    public String getModel() {
//        return model;
//    }
//
//
//    public void setMax_tokens(int max_tokens) {}
//    public int getMax_tokens() {
//        return max_tokens;
//    }
//
//
//    public void setTemperature(double temperature) {}
//    public double getTemperature() {
//        return temperature;
//    }
//
//
//    public void setTop_p(double top_p) {}
//    public double getTop_p() {
//        return top_p;
//    }
//
//
//    public void setStream(boolean stream) {}
//    public boolean getStream() {
//        return stream;
//    }


//    @Data
//    public static class Message {
//        private String role;
//        private String content;
//
//        public Message(String role, String content) {}
//        public Message() {}
//
//        public String getRole() {
//            return role;
//        }
//        public String getContent() {
//            return content;
//        }
//        public void setContent(String content) {}
//        public void setRole(String role) {}
//
//    }

}