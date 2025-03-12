package com.example.SpringJpa.entity.inheritancestrategy;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CheckPayment extends Payment {
    private String checkNumber;
}
