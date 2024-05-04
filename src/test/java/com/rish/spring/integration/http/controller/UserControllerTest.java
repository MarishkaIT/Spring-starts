package com.rish.spring.integration.http.controller;

import com.rish.spring.database.entity.Role;
import com.rish.spring.dto.UserCreateEditDto;
import com.rish.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.rish.spring.dto.UserCreateEditDto.Fields.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize.hasSize;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mvc;

    @BeforeEach
    void init() {
//        List<GrantedAuthority> roles = Arrays.asList(Role.ADMIN, Role.USER);
//        User testUser = new User("test@gmail.com", "test", roles);
//        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), roles);
//
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//        securityContext.setAuthentication(authenticationToken);
//        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void findAll() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void create() throws Exception {
        mvc.perform(post("/users")
                .param(username, "test@gmail.com")
                .param(firstname, "Test")
                .param(lastname, "Tests")
                .param(role, "ADMIN")
                .param(companyId, "1")
                        .param(birthDate, "2000-10-02")
        )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}