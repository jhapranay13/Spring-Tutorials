package com.example.SpringMongoDb.documents;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    private String name;
    private int score;
}
