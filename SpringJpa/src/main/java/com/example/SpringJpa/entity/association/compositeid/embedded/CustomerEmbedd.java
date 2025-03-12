package com.example.SpringJpa.entity.association.compositeid.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CustomerEmbedd {
    @EmbeddedId
    private EmbeddIdCustomer embeddIdCustomer;

    private String name;
}
