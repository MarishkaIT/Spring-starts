package com.rish.spring.mapper;

import com.rish.spring.database.entity.Company;
import com.rish.spring.dto.CompanyReadDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto>{
    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName()
        );
    }
}
