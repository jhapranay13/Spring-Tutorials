package com.example.SpringJpa.entity.inheritancestrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Publication {
    @Id
    private int id;
    private String name;
}
