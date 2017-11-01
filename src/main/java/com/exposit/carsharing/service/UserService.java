package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.UserRequest;
import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;

public interface UserService {
    UserResponse createUser(UserRequest userRequest) throws EntityAlreadyExistException;

    UserResponse createAdmin(UserRequest userRequest) throws EntityAlreadyExistException;
}
