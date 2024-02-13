package com.rish.spring.service;

import com.rish.spring.database.entity.Company;
import com.rish.spring.database.repository.CrudRepository;
import com.rish.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;


}
