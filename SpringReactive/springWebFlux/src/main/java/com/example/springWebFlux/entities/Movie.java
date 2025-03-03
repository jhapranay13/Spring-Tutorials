package com.example.springWebFlux.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data
public class Movie {
    @NotNull(message = "Id cannot be null")
    private String id;

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Genre cannot be null")
    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    public Movie(String id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }
}

