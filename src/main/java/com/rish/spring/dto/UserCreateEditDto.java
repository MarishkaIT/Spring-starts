package com.rish.spring.dto;

import com.rish.spring.database.entity.Role;
import com.rish.spring.validation.UserInfo;
import com.rish.spring.validation.group.CreateAction;
import com.rish.spring.validation.group.UpdateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {
    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    LocalDate birthDate;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    Role role;

    Integer companyId;

    MultipartFile image;
}
