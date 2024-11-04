package com.example.bill_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    String id;

    String name;
    Integer expire;
    Integer promotion;
    String desc;
    Integer price;

}
