package com.example.coc.service;

import com.example.coc.model.Prompt;
import com.fasterxml.jackson.databind.JsonNode;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromptEvaluationService {

    private final KieSession kieSession;

    @Autowired(required = false)
    private OpenAIService openAIService;

    public PromptEvaluationService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public Prompt evaluatePrompt(String text) {
        Prompt prompt = new Prompt();
        prompt.setText(text);

        // Optional OpenAI integration
        if (openAIService != null) {
            System.out.println("Calling OpenAI Moderation API...");
            try {
                JsonNode openaiResult = openAIService.moderateText(text).block();
                if (openaiResult != null && openaiResult.has("results")) {
                    JsonNode firstResult = openaiResult.get("results").get(0);
                     if (firstResult != null && firstResult.has("flagged") && firstResult.get("flagged").asBoolean()) {
                         System.out.println("OpenAI flagged the prompt. Details: " + firstResult);
                     } else {
                         System.out.println("OpenAI did not flag the prompt.");
                     }
                }
            } catch (Exception e) {
                System.err.println("Failed to get response from OpenAI: " + e.getMessage());
            }
        } else {
             System.out.println("OpenAI Service not enabled (API key not provided). Skipping OpenAI moderation.");
        }

        // Drools Evaluation
        kieSession.insert(prompt);
        kieSession.fireAllRules();

        return prompt;
    }
} 