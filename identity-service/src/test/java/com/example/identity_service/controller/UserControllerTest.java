package com.example.identity_service.controller;

import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse response;
    private LocalDate dob;
    @BeforeEach
    void initData() {
        dob = LocalDate.of(2004, 9, 27);
        request = UserCreationRequest.builder()
                .username("john")
                .firstName("john")
                .lastName("doe")
                .password("12345678")
                .dob(dob)
                .build();

        response = UserResponse.builder()
                .id("c1214141")
                .username("john")
                .firstName("john")
                .lastName("doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser() {
        log.info("Hello test");
    }
}
