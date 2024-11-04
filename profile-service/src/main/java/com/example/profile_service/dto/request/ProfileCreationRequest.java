package com.example.profile_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    String userId;
    @Nullable
    String firstName;
    @Nullable
    String lastName;
    @Nullable
    String email;
    @Nullable
    LocalDate dob;
    @Nullable
    String city;
    @Nullable
    String profilePic;
}
