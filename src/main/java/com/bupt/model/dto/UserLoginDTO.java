package com.bupt.model.dto;

import com.bupt.service.UserService;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录入参
 */
@Data
public class UserLoginDTO {
    @NotBlank
    @Length(min = 3, max = 20)
    private String username;
    @NotBlank
    @Length(min = UserService.PSW_MIN_LEN, max = 20)
    private String password;
}
