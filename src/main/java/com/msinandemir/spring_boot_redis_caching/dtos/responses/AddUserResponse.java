package com.msinandemir.spring_boot_redis_caching.dtos.responses;

import java.time.Instant;

public record AddUserResponse(
        Long id,
        Instant createdAt,
        Instant updatedAt,
        String username,
        String password
) {
}
