package com.example.SpringJpa.entity.association.manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Programmer> programmers;
}
