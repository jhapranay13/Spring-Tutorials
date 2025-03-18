package com.example.SpringAIIntro.services;

import com.example.SpringAIIntro.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIService {
    private final ChatModel chatModel;

    @Value("classpath:templates/get-capitol-prompt.st")
    private Resource resource;

    @Value("classpath:templates/get-capitol-with-info.st")
    private Resource resourceWithInfo;

    @Value("classpath:templates/get-capitol-prompt-json.st")
    private Resource resourceWithJson;

    @Value("classpath:templates/get-capitol-prompt-json-schema.st")
    private Resource resourceWithJsonSchema;
    
    @Autowired
    ObjectMapper objectMapper;

    public OpenAIService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public CapitolResponse getCapitol(CapitolRequest request) {
        //PromptTemplate promptTemplate = new PromptTemplate(request.stateOrCountry());
        PromptTemplate promptTemplate = new PromptTemplate(resource);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);

        return new CapitolResponse(response.getResult().getOutput().getText());
    }

    public CapitolResponse getCapitolWithInfo(CapitolRequest request) {
        //PromptTemplate promptTemplate = new PromptTemplate(request.stateOrCountry());
        PromptTemplate promptTemplate = new PromptTemplate(resourceWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);

        return new CapitolResponse(response.getResult().getOutput().getText());
    }

    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getText());
    }

    public Answer getCapitolWithJson(CapitolRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(resourceWithJson);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);
        System.out.println(response.getResult().getOutput().getText());
        String responseString = null;

        try {
            String str = response.getResult().getOutput().getText();
            JsonNode jsonNode = objectMapper.readTree(str
                    .substring(7, str.length() - 3));
            responseString = jsonNode.get("answer").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Answer(responseString);
    }

    public SchemaResponse getCapitolWithJsonSchema(CapitolRequest request) {
        BeanOutputConverter<SchemaResponse> converter = new BeanOutputConverter<>(SchemaResponse.class);
        String format = converter.getFormat();
        System.out.println("Format >> " + format);

        PromptTemplate promptTemplate = new PromptTemplate(resourceWithJsonSchema);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry(),
                "format", format));
        ChatResponse response = chatModel.call(prompt);
        String str = response.getResult().getOutput().getText();
        System.out.println("Message Recieved >> " +str);

        return converter.convert(str);
    }
}
