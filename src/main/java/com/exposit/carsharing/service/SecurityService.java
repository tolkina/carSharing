package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;

public interface SecurityService {
    void autoLogin(String username, String password);

    String findLoggedInUsername();

    String getPrincipalUsername() throws UnauthorizedException;

    UserResponse getPrincipal() throws UnauthorizedException;

    Long getPrincipalId() throws UnauthorizedException, EntityNotFoundException;
}
