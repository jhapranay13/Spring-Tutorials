package com.example.RetrievalAugmentedGeneration.activity;

import com.example.RetrievalAugmentedGeneration.model.Answer;
import com.example.RetrievalAugmentedGeneration.model.Question;
import com.example.RetrievalAugmentedGeneration.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionActivity {

    private final OpenAIService openAIService;

    public QuestionActivity(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
}
