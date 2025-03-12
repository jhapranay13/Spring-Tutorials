package com.example.SpringJpa.entity.inheritancestrategy;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class MyProduct {
    @Id
    private int id;
    private String name;
}
