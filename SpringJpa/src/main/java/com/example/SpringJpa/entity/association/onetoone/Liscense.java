package com.example.SpringJpa.entity.association.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Entity
public class Liscense {
    @Id
    private int id;
    private String liscenseNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    Person person;
}
