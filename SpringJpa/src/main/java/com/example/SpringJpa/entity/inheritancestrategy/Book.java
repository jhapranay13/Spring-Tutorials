package com.example.SpringJpa.entity.inheritancestrategy;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue("2")
public class Book extends MyProduct {
    private String author;
}
