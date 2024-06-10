package com.msinandemir.spring_boot_redis_caching.services.abstracts;

import com.msinandemir.spring_boot_redis_caching.dtos.requests.AddUserRequest;
import com.msinandemir.spring_boot_redis_caching.dtos.responses.AddUserResponse;
import com.msinandemir.spring_boot_redis_caching.dtos.responses.GetUserResponse;

import java.util.List;

public interface UserService {
    List<GetUserResponse> getAllUsers();

    GetUserResponse getUserById(Long userId);

    AddUserResponse addUser(AddUserRequest request);

    void deleteUserById(Long userId);
}
