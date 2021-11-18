package com.bupt.model.dto;

import com.bupt.service.UserService;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户注册入参
 */
@Data
public class UserRegisterDTO {
    @NotBlank
    @Length(min = 3, max = 20)
    private String username;

    @NotBlank
    @Length(min = UserService.PSW_MIN_LEN, max = 20)
    private String password;

    @NotBlank
    @Length(min = 18, max = 18)
    private String idNo;

    @NotNull
    private Integer city;

    @NotNull
    private Integer communityType;
}
