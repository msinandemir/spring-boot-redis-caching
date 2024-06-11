package com.msinandemir.spring_boot_redis_caching.services.concretes;

import com.msinandemir.spring_boot_redis_caching.dtos.requests.AddUserRequest;
import com.msinandemir.spring_boot_redis_caching.dtos.responses.AddUserResponse;
import com.msinandemir.spring_boot_redis_caching.dtos.responses.GetUserResponse;
import com.msinandemir.spring_boot_redis_caching.models.User;
import com.msinandemir.spring_boot_redis_caching.repositories.UserRepository;
import com.msinandemir.spring_boot_redis_caching.services.abstracts.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Cacheable(cacheNames = "users", key = "#root.methodName", unless = "#result == null")
    @Override
    public List<GetUserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> new GetUserResponse(user.getId(), user.getCreatedAt(), user.getUpdatedAt(), user.getUsername(), user.getPassword())
                ).collect(Collectors.toList());
    }


    @Cacheable(cacheNames = "user", key = "'#root.methodName' + #userId", unless = "#result == null")
    @Override
    public GetUserResponse getUserById(Long userId) {
        User foundUser = findUserById(userId);
        return new GetUserResponse(foundUser.getId(), foundUser.getCreatedAt(), foundUser.getUpdatedAt(), foundUser.getUsername(), foundUser.getPassword());
    }

    @CacheEvict(cacheNames = {"users", "user"}, allEntries = true)
    @Override
    public AddUserResponse addUser(AddUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());

        User savedUser = userRepository.save(user);
        return new AddUserResponse(savedUser.getId(), savedUser.getCreatedAt(), savedUser.getUpdatedAt(), savedUser.getUsername(), savedUser.getPassword());
    }

    @CacheEvict(cacheNames = {"users", "user"}, allEntries = true)
    @Override
    public void deleteUserById(Long userId) {
        User foundUser = findUserById(userId);
        userRepository.deleteById(foundUser.getId());
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
