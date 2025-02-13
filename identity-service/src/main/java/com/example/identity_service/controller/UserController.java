package com.example.identity_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.identity_service.dto.request.ApiResponse;
import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.request.UserUpdateRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("1");
        var result = userService.createUser(request);
        return ApiResponse.<UserResponse>builder().result(result).build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        var result = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder().result(result).build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        var result = userService.getUser(userId);
        return ApiResponse.<UserResponse>builder().result(result).build();
    }

    @PutMapping("/updateInfo")
    ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request) {
        log.info("Request: {}", request);
        var result = userService.updateUser(request);
        return ApiResponse.<UserResponse>builder().result(result).build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getUser() {
        var result = userService.getMyInfo();
        return ApiResponse.<UserResponse>builder().result(result).build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }
}
