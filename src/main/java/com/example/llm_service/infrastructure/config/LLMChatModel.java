package com.example.llm_service.infrastructure.config;

import com.example.llm_service.domain.dto.LLMChatRequest;
import com.example.llm_service.domain.dto.LLMChatResponse;
import com.example.llm_service.domain.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LLMChatModel implements ChatModel {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;
    private final String model;
    private final int maxTokens;
    private final double temperature;
    private final double topP;
    private final boolean stream;
    private final Logger LOGGER = LoggerFactory.getLogger("LLMChatModel");

    public LLMChatModel(
            @Autowired RestTemplate restTemplate,
            @Value("${spring.ai.llm.base-url}") String baseUrl,
            @Value("${spring.ai.llm.api-key}") String apiKey,
            @Value("${spring.ai.llm.model}") String model,
            @Value("${spring.ai.llm.max-tokens}") int maxTokens,
            @Value("${spring.ai.llm.temperature}") double temperature,
            @Value("${spring.ai.llm.top-p}") double topP,
            @Value("${spring.ai.llm.stream}") boolean stream
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
        this.stream = stream;
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        System.out.println("prompt " + prompt.toString());
        System.out.println("Prompt content: " + prompt.getInstructions());


        LLMChatRequest request = toLLMRequest(prompt);

        System.out.println("request " + request.toString());

        System.out.println("request.getMessages() " + request.getMessages());

        try {
            String jsonBody = new ObjectMapper().writeValueAsString(request);
            System.out.println("Request JSON: " + jsonBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

//        create http request entity
        HttpEntity<LLMChatRequest> httpEntity = new HttpEntity<>(request, headers);

        // send http request entity
        ResponseEntity<LLMChatResponse> responseEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                httpEntity,
                LLMChatResponse.class
        );

        // convert response to chatResponse
        HttpStatusCode httpStatusCode = responseEntity.getStatusCode();
        Integer statusCode = responseEntity.getStatusCodeValue();
        if (httpStatusCode.is2xxSuccessful()) {
            LLMChatResponse response = responseEntity.getBody();

            ChatResponse chatResponse = toChatResponse(response, statusCode);

            return chatResponse;
        } else {
            throw new RuntimeException("API Error: " + responseEntity.getStatusCode() + " - " + responseEntity.getBody());
        }
    }


    private LLMChatRequest toLLMRequest(Prompt prompt) {
        LLMChatRequest request = new LLMChatRequest();
        List<Message> messages = prompt.getInstructions().stream()
                .filter(m -> m instanceof UserMessage)
                .map(msg -> {
                    Message message = new Message();

                    if (msg instanceof UserMessage) {
                        System.out.println("UserMessage " + msg.toString());
                        System.out.println("mesage type " + msg.getMessageType());
                        message.setRole("user");
                        message.setContent(((UserMessage) msg).getContent());
                    }

                    System.out.println("Message: " + message.getRole() + " - " + message.getContent());

                    return message;
                }).toList();

        request.setMessages(messages);
        request.setModel(model);
        request.setMax_tokens(maxTokens);
        request.setTemperature(temperature);
        request.setTop_p(topP);
        request.setStream(stream);

        System.out.println("Request: " + request);
        return request;
    }


    private ChatResponse toChatResponse(LLMChatResponse response, Integer statusCode) {
        String answer = response.getChoices().get(0).getMessage().getContent();
        Map<String, Object> properties = new HashMap<>();
        Integer tokens = response.getUsage().getTotal_tokens();
        properties.put("tokens", tokens);
        properties.put("statusCode", statusCode);

        AssistantMessage assistantMessage = new AssistantMessage(answer, properties);
        return new ChatResponse(List.of(new Generation(assistantMessage)));
    }
}
