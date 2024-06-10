package com.msinandemir.spring_boot_redis_caching.repositories;

import com.msinandemir.spring_boot_redis_caching.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
