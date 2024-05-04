package com.rish.spring.database.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.rish.spring.database.entity.Role;
import com.rish.spring.database.entity.User;
import com.rish.spring.database.querydsl.QPredicates;
import com.rish.spring.dto.PersonalInfo;
import com.rish.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.rish.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    //language=PostgreSQL
    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
            firstname,
            lastname,
            birth_date
            FROM users
            WHERE company_id = ?
            AND role = ?
            """;

    private static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id =?,
                role = ?
            WHERE id = ?    
            """;

    private static final String UPDATE_COMPANY_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId,
                role = :role
            WHERE id = :id    
            """;

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();
        return new JPAQuery<User>(entityManager)
               .select(user)
               .from(user)
               .where(predicate)
                .fetch();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate()
        ), companyId, role.name());
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        List<Object[]> args = users.stream()
                .map(user1 -> new Object[]{user1.getCompany().getId(), user1.getRole().name(), user1.getId()})
                .toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }

    @Override
    public void updateCompanyAndRoleNamed(List<User> users) {
        MapSqlParameterSource[] args = users.stream()
                .map(user1 -> Map.of(
                        "companyId", user1.getCompany().getId(),
                        "role", user1.getRole().name(),
                        "id", user1.getId()
                ))
                .map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);

        namedJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED, args);
    }
}
