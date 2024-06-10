package com.msinandemir.spring_boot_redis_caching.dtos.responses;

import java.util.Date;

public record AddUserResponse(
        Long id,
        Date createdAt,
        Date updatedAt,
        String username,
        String password
) {
}
