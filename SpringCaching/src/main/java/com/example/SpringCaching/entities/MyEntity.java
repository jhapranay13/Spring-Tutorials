package com.example.SpringCaching.entities;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyEntity implements Serializable {
    private int id;
    private String name;
    private String desc;
}
