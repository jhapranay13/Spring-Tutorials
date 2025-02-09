package com.springrestandcloud.beans;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Student desc"
)
public class Student {
    @Schema(
            description = "Id field"
    )
    private int id;
    @Schema(
            description = "FirstName"
    )
    private String firstName;
    @Schema(
            description = "LastName"
    )
    private String lastName;

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
