package com.example.bill_service.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Builder
@Document(value = "bills")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {
    @MongoId
    Object id;

    String billId;

    String name;
    Integer expire;
    Integer promotion;
    String desc;
    Integer price;

}
