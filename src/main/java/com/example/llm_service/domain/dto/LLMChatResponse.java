package com.example.llm_service.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class LLMChatResponse {
    private List<Choice> choices;
    private String model;
    private String id;

//    public LLMChatResponse() {}
//    public LLMChatResponse(List<Choice> choices, String model, String id) {}
//
//    public void setChoices(List<Choice> choices) {}
//    public void setModel(String model) {}
//    public void setId(String id) {}
//    public List<Choice> getChoices() {
//        return choices;
//    }
//    public String getModel() {
//        return model;
//    }
//    public String getId() {
//        return id;
//    }

    @Data
    public static class Choice {
        private Message message;
        private String finish_reason;

//        public Choice(Message message, String finish_reason) {}
//        public Choice() {}
//        public void setMessage(Message message) {}
//        public void setFinishReason(String finish_reason) {}
//        public Message getMessage() {
//            return message;
//        }
//        public String getFinishReason() {
//            return finish_reason;
//        }

        @Data
        public static class Message {
            private String role;
            private String content;
//
//            public Message() {}
//            public Message(String role, String content) {}
//            public String getRole() {
//                return role;
//            }
//            public void setRole(String role) {}
//            public String getContent() {
//                return content;
//            }
//            public void setContent(String content) {}
//        }


        }
    }
}