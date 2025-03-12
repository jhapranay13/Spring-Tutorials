package com.example.SpringJpa.entity.association.manytomany;

import com.example.SpringJpa.entity.association.onetomany.Phone;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Data  this cannot be used or else it will go in infinite loop and will result in stack overflow
@Entity
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "programmers_project",
            joinColumns =@JoinColumn(name = "programmer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private Set<Project> projects;
}
