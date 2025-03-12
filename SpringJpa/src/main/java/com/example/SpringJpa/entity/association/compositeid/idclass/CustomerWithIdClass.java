package com.example.SpringJpa.entity.association.compositeid.idclass;

import com.example.SpringJpa.entity.association.compositeid.embedded.EmbeddIdCustomer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(CustomerId.class)
public class CustomerWithIdClass {
    @Id
    private int id;
    @Id
    private String email;

    private String name;
}
