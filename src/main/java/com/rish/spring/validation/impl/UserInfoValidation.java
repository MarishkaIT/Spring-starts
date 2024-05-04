package com.rish.spring.validation.impl;

import com.rish.spring.database.repository.CompanyRepository;
import com.rish.spring.dto.UserCreateEditDto;
import com.rish.spring.validation.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class UserInfoValidation implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    private final CompanyRepository companyRepository;
    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstname()) || hasText(value.getLastname());
    }
}
