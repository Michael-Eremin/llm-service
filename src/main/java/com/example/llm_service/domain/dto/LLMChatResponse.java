package com.example.llm_service.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class LLMChatResponse {
    private List<Choice> choices;
    private String model;
    private String id;
    private Usage usage;

    @Data
    public static class Choice {
        private Message message;
        private String finish_reason;

        @Data
        public static class Message {
            private String role;
            private String content;

        }
    }

    @Data
    public static class Usage {
        private Integer prompt_tokens;
        private Integer total_tokens;
        private Integer completion_tokens;
    }
}