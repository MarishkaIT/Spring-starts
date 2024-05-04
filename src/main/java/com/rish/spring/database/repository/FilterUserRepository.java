package com.rish.spring.database.repository;

import com.rish.spring.database.entity.Role;
import com.rish.spring.database.entity.User;
import com.rish.spring.dto.PersonalInfo;
import com.rish.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
