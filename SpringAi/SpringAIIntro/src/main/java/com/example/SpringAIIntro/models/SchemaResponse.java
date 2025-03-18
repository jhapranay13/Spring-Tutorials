package com.example.SpringAIIntro.models;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record SchemaResponse(@JsonPropertyDescription("This is the city name") String answer) {
}
