package com.example.SpringJpa.entity;

import jakarta.persistence.*;
import lombok.*;

//@Table need if table name is different from entity name
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column needed if column name is different from property
    private String name;
    private String desc;
    private double price;
}
