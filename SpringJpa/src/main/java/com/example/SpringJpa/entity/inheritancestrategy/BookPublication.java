package com.example.SpringJpa.entity.inheritancestrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class BookPublication extends Publication{
    @Id
    private int id;
    private String bookName;
}
