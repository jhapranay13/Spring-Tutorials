package com.example.SpringJpa.entity.association.onetoone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Person {
    @Id
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Liscense liscense;
}
