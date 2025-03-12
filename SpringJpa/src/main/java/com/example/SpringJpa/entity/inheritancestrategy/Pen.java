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
@DiscriminatorValue("1")
public class Pen extends MyProduct{
    private String color;
}
