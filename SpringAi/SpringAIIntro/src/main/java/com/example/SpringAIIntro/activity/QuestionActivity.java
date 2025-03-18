package com.example.SpringAIIntro.activity;

import com.example.SpringAIIntro.models.*;
import com.example.SpringAIIntro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionActivity {

    private final OpenAIService openAIService;


    public QuestionActivity(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/capitol")
    public CapitolResponse askCapitol(@RequestBody CapitolRequest request) {
        System.out.println("Asked >> " + request.stateOrCountry());
        return openAIService.getCapitol(request);
    }

    @PostMapping("/capitol/json")
    public Answer askCapitolJson(@RequestBody CapitolRequest request) {
        System.out.println("Asked >> " + request.stateOrCountry());
        return openAIService.getCapitolWithJson(request);
    }

    @PostMapping("/capitol/jsonschema")
    public SchemaResponse askCapitolJsonschema(@RequestBody CapitolRequest request) {
        System.out.println("Asked >> " + request.stateOrCountry());
        return openAIService.getCapitolWithJsonSchema(request);
    }

    @PostMapping("/capitol/info")
    public CapitolResponse askCapitolWithInfo(@RequestBody CapitolRequest request) {
        System.out.println("Asked >> " + request.stateOrCountry());
        return openAIService.getCapitolWithInfo(request);
    }

    @PostMapping
    public Answer askQuestion(@RequestBody Question question) {
        System.out.println("Asked >> " + question.question());
        return openAIService.getAnswer(question);
    }
}
