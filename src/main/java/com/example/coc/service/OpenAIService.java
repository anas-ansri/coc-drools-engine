package com.example.coc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@ConditionalOnProperty(name = "openai.api.key")
public class OpenAIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public OpenAIService(@Value("${openai.api.key}") String apiKey, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/moderations")
                                        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .build();
        this.objectMapper = new ObjectMapper();
        System.out.println("OpenAIService initialized with API key.");
    }

    public Mono<JsonNode> moderateText(String text) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("input", text);

        try {
            String jsonBody = objectMapper.writeValueAsString(requestBody);

            return webClient.post()
                            .body(BodyInserters.fromValue(jsonBody))
                            .retrieve()
                            .bodyToMono(JsonNode.class)
                            .doOnError(e -> System.err.println("Error calling OpenAI Moderation API: " + e.getMessage()));

        } catch (Exception e) {
            System.err.println("Error creating OpenAI request body: " + e.getMessage());
            return Mono.error(e);
        }
    }
} 