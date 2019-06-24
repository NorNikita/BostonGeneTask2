package com.bostongene.nikita.api.users;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserBodyContainer {
    @Email(message = "Email not valid") private String email;
    @NotNull private String password;
    @NotNull private String firstName;
    @NotNull private String lastName;
    private Date birthday;
}
