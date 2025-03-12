package com.example.SpringJpa.entity.association.compositeid.idclass;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerId implements Serializable {
    private int id;
    private String email;
}
