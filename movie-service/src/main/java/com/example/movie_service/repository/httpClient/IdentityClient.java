package com.example.movie_service.repository.httpClient;

import com.example.movie_service.configuration.AuthenticationRequestInterceptor;
import com.example.movie_service.dto.ApiResponse;
import com.example.movie_service.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "profile-service",
        url = "${app.services.identity}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface IdentityClient {
    @GetMapping(value = "/users/myInfo")
    ApiResponse<UserResponse> getMyInfo();
}


