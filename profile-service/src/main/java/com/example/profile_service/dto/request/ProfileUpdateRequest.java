package com.example.profile_service.dto.request;

import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileUpdateRequest {
    @Nullable
    String firstName;
    @Nullable
    String lastName;
    @Nullable
    LocalDate dob;
    @Nullable
    String city;
    @Nullable
    String profilePic;
}
