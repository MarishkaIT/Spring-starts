package com.rish.spring.dto;

import com.rish.spring.database.entity.Role;
import lombok.Value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    String image;
    @Enumerated(EnumType.STRING)
    Role role;
    CompanyReadDto company;
}
