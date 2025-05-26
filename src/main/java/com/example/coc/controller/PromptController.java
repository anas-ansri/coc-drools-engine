package com.example.coc.controller;

import com.example.coc.model.Prompt;
import com.example.coc.service.PromptEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/prompt")
@Tag(name = "Prompt Evaluation", description = "Evaluate text prompts against Code of Conduct rules.")
public class PromptController {

    private final PromptEvaluationService evaluationService;

    public PromptController(PromptEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Operation(summary = "Evaluate a prompt for Code of Conduct violations",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Prompt evaluation successful",
                                content = @Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = Prompt.class)))
               })
    @PostMapping("/evaluate")
    public Prompt evaluatePrompt(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        if (text == null) {
            Prompt errorPrompt = new Prompt();
            errorPrompt.setText(payload.get("text"));
            errorPrompt.setViolation("Error: 'text' field is missing in request body.");
            errorPrompt.setSeverityScore(100);
            return errorPrompt;
        }
        return evaluationService.evaluatePrompt(text);
    }
} 