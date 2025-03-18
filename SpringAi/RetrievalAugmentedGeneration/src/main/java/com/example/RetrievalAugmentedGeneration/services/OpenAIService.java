package com.example.RetrievalAugmentedGeneration.services;

import com.example.RetrievalAugmentedGeneration.model.Answer;
import com.example.RetrievalAugmentedGeneration.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class OpenAIService {

    private final ChatModel chatModel;
    private final SimpleVectorStore simpleVectorStore;

    @Value("classpath:/templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Autowired
    public OpenAIService(ChatModel chatModel, SimpleVectorStore simpleVectorStore) {
        this.chatModel = chatModel;
        this.simpleVectorStore = simpleVectorStore;
    }


    public Answer getAnswer(Question question) {
        List<Document> documents = simpleVectorStore.similaritySearch(SearchRequest.builder()
                .query(question.question()).topK(4).build());
        String contentList = documents.stream().map(Document::getText).collect(Collectors.joining());

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of("input", question.question(),
                "documents",  contentList));
        System.out.println(contentList);
        System.out.println(prompt.getContents());
      //  ChatResponse response = chatModel.call(prompt);
        ChatResponse response = ChatClient.builder(chatModel).build().prompt(prompt)
                .advisors(new QuestionAnswerAdvisor(simpleVectorStore))
                .user(question.question())
                .call().chatResponse();
        return new Answer(response.getResult().getOutput().getText());
    }
}