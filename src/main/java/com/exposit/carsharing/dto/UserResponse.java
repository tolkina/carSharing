package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class UserResponse implements Serializable {
    private Long id;
    private String email;
    private Collection<RoleResponse> roles;
}
