package com.example.SpringJpa.entity.association.compositeid.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EmbeddIdCustomer {
    // in case of embedded id our JPQL will become like
    // select c.embeddIdCustomer.email from CustomerEmbedd c
    private int id;
    private String email;
}
