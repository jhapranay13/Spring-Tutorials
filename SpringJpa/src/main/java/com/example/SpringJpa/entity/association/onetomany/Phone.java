package com.example.SpringJpa.entity.association.onetomany;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String number;
    private String type;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
