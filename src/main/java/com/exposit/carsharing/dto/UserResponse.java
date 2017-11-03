package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class UserResponse extends AbstractResponse {
    private String email;
    private Collection<RoleResponse> roles;
}
