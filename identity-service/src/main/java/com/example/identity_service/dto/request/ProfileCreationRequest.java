package com.example.identity_service.dto.request;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    String userId;
    String firstName;
    String email;
    @Nullable
    String lastName;

    @Nullable
    LocalDate dob;

    @Nullable
    String city;

    @Nullable
    String profilePic;
}
