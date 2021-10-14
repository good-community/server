package com.bupt.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录入参
 */
@Data
public class UserAuthDTO {
    @NotBlank
    @Length(min = 3, max = 20)
    private String username;
    @NotBlank
    @Length(min = 3, max = 20)
    private String password;
}
