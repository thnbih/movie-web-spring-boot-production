package com.example.bill_service.mapper;

import com.example.bill_service.dto.response.BillResponse;
import com.example.bill_service.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mapping(source = "billId", target = "id")
    BillResponse toBillResponse(Bill request);
}
