package com.example.SpringAIIntro.services;

import com.example.SpringAIIntro.models.Answer;
import com.example.SpringAIIntro.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    public void getAnswerTest() {
        Answer answer = openAIService.getAnswer(new Question("Tell me a dad joke"));
        System.out.println(answer.answer());
    }

}