package com.springrestandcloud.beans;

import com.springrestandcloud.customValidation.PhoneValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    @NotEmpty
    String name;

    @NotEmpty
    @Email
    String email;

    @PhoneValid
    String phoneNumber;
}
