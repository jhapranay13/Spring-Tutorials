package com.example.SpringJpa.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private int zip;
    private String state;
    private String country;
}
