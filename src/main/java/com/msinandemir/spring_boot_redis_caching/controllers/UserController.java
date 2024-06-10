package com.msinandemir.spring_boot_redis_caching.controllers;

import com.msinandemir.spring_boot_redis_caching.dtos.requests.AddUserRequest;
import com.msinandemir.spring_boot_redis_caching.dtos.responses.AddUserResponse;
import com.msinandemir.spring_boot_redis_caching.dtos.responses.GetUserResponse;
import com.msinandemir.spring_boot_redis_caching.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    ResponseEntity<List<GetUserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{userId}")
    ResponseEntity<GetUserResponse> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest request){
        return new ResponseEntity<>(userService.addUser(request), HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}")
    void deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }
}
