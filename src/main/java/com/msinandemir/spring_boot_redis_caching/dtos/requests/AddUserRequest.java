package com.msinandemir.spring_boot_redis_caching.dtos.requests;

public record AddUserRequest(
        String username,
        String password
) {
}
