package com.example.bill_service.controller;

import com.example.bill_service.dto.ApiResponse;
import com.example.bill_service.dto.request.CreateBillRequest;
import com.example.bill_service.dto.response.BillResponse;
import com.example.bill_service.dto.response.PaymentClientResponse;
import com.example.bill_service.service.BillService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillController {
    BillService billService;

    @GetMapping("/getAll")
    ApiResponse<List<BillResponse>> getAll() {
        return ApiResponse.<List<BillResponse>>builder()
                .result(billService.getAllBill())
                .build();
    }

    @GetMapping("/get")
    ApiResponse<BillResponse> getBillById(@RequestBody @Valid CreateBillRequest request) {
        return ApiResponse.<BillResponse>builder()
                .result(billService.getBillById(request.getId()))
                .build();
    }

    @PostMapping("/payment")
    ApiResponse<Object> createPayment(@RequestBody @Valid CreateBillRequest request) throws Exception {
        return ApiResponse.<Object>builder()
                .result(billService.creatBill(request.getId()))
                .build();
    }

    @PostMapping("/callback")
    ApiResponse<String> callback() {
        billService.callBack();
        return ApiResponse.<String>builder()
                .result("Ok")
                .build();
    }
}
