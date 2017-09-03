package com.mifinity.demo.adapter.rest.dto;

import com.mifinity.demo.utils.UniqueUser;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Getter
@Builder
public class UserDto {

    @NotNull
    @UniqueUser
    private String username;

    @NotNull
    private String password;
}
