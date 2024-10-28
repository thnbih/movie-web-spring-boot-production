package com.example.identity_service.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.example.identity_service.constant.PredefinedRole;
import com.example.identity_service.mapper.ProfileMapper;
import com.example.identity_service.repository.httpClient.ProfileClient;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.request.UserUpdateRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entiy.User;
import com.example.identity_service.entiy.Role;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.UserMapper;
import com.example.identity_service.repository.RoleRepository;
import com.example.identity_service.repository.UserRepostitory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepostitory userRepostitory;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    ProfileClient profileClient;
    ProfileMapper profileMapper;


    public UserResponse createUser(UserCreationRequest request) {
        if (userRepostitory.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findById(PredefinedRole.USER_ROLE).orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE));
        roles.add(userRole);
        user.setRoles(roles);
        user = userRepostitory.save(user);
        var profileRequest = profileMapper.toProfileCreationrequest(request);
        profileRequest.setUserId(user.getId());
         profileClient.createProfile(profileRequest);


        return userMapper.toUserReponse(user);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepostitory
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserReponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers() {
        log.info("In method get user");
        return userRepostitory.findAll().stream().map(userMapper::toUserReponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserReponse(
                userRepostitory.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepostitory.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserReponse(userRepostitory.save(user));
    }

    public void deleteUser(String userId) {
        userRepostitory.deleteById(userId);
    }
}
