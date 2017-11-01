package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;

public interface SecurityService {
    void autoLogin(String username, String password);

    String findLoggedInEmail();

    String getPrincipalEmail() throws UnauthorizedException;

    Long getPrincipalId() throws UnauthorizedException, EntityNotFoundException;
}
