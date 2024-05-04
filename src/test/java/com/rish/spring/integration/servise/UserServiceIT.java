package com.rish.spring.integration.servise;

import com.rish.spring.database.entity.Role;
import com.rish.spring.dto.UserCreateEditDto;
import com.rish.spring.dto.UserReadDto;
import com.rish.spring.integration.IntegrationTestBase;
import com.rish.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT extends IntegrationTestBase {

    //    @Autowired
    private final UserService userService;
    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("mika@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        UserReadDto actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-123L));
        assertTrue(userService.delete(USER_1));
    }
}
