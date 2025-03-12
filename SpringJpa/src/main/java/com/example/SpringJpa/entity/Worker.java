package com.example.SpringJpa.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Worker {
    @Id
    private int id;
    private String name;
    @Embedded
    private Address address;

}
