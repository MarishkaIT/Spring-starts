package com.rish.spring.integration.database;

import com.rish.spring.database.entity.Role;
import com.rish.spring.database.entity.User;
import com.rish.spring.database.repository.UserRepository;
import com.rish.spring.dto.PersonalInfo;
import com.rish.spring.dto.UserFilter;
import com.rish.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    @Autowired
    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }


    @Test
    void checkJdbcTemplate() {
        List<PersonalInfo> user = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
         assertThat(user).hasSize(1);
    }

    @Test
    void checkAuditing() {
        User mira = userRepository.findById(1L).get();
        mira.setBirthDate(mira.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation(){
        UserFilter filter = new UserFilter(null, "ov", LocalDate.now());
        var users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(1);
    }


    @Test
    void checkProjection() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }


    @Test
    void checkPageable() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()){
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort() {
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sort = sortBy.by(User::getFirstname)
                .and(sortBy.by(User::getLastname));

        Sort sortById = Sort.by("firstname").and(Sort.by("lastname"));
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }


    @Test
    void checkUpdate() {
        User mira = userRepository.getReferenceById(1L);
        assertSame(Role.ADMIN, mira.getRole());
        mira.setBirthDate(LocalDate.now());

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameMira = userRepository.getReferenceById(1L);
        assertSame(Role.USER, theSameMira.getRole());
    }

    @Test
    void checkQueries(){
        List<User> users = userRepository.findAllBy("e", "ov");
        assertThat(users).hasSize(1);
    }

}