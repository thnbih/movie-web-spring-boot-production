package com.example.bill_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentClientResponse {
    Integer return_code;
    String return_message;
    Integer sub_return_code;
    String sub_return_message;
    String zp_trans_token;
    String order_url;
    String order_token;
}
