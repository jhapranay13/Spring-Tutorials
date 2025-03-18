package com.example.SpringMongoDb.documents.reference;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "refdepartment")
public class Department {
    @Id
    private String id;
    private String name;
    private String location;
}
