package com.example.bill_service.repostiory;

import com.example.bill_service.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BillRepository extends MongoRepository<Bill, String> {
    Optional<Bill> findByBillId(String id);
}
