package com.example.SpringMongoDb.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "student")  // only use when it is collection
public class Student {
    @Id
    private String id;
    private String name;
    @Field(name = "mail")  // if field name and variable name is same we don't need this
    private String email;
    private Department department;
    private List<Subject> subjects;
}
