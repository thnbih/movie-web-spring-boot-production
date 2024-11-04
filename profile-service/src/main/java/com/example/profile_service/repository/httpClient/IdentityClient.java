package com.example.profile_service.repository.httpClient;

import com.example.profile_service.configuration.AuthenticationRequestInterceptor;
import com.example.profile_service.dto.ApiResponse;
import com.example.profile_service.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "profile-service",
        url = "${app.services.identity}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface IdentityClient {
    @GetMapping(value = "/users/myInfo")
    ApiResponse<UserResponse> getMyInfo();
}