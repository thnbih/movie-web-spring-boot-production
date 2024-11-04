package com.example.bill_service.service;

import com.example.bill_service.crypto.HMACUtil;
import com.example.bill_service.dto.request.EmbedData_Request;
import com.example.bill_service.dto.request.PaymentClientRequest;
import com.example.bill_service.dto.response.BillResponse;
import com.example.bill_service.dto.response.PaymentClientResponse;
import com.example.bill_service.dto.response.UserResponse;
import com.example.bill_service.entity.Bill;
import com.example.bill_service.exception.AppException;
import com.example.bill_service.exception.ErrorCode;
import com.example.bill_service.mapper.BillMapper;
import com.example.bill_service.repostiory.BillRepository;
import com.example.bill_service.repostiory.httpClient.IdentityClient;
import com.example.bill_service.repostiory.httpClient.PaymentClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillService {
    IdentityClient identityClient;
    BillRepository billRepository;
    BillMapper billMapper;
    PaymentClient paymentClient;

    @Value("${app.id}")
    @NonFinal
    private String appId;

    @Value("${app.key1}")
    @NonFinal
    private String key1;

    @Value("${app.key2}")
    @NonFinal
    private String key2;

    @Value("${app.endpoint}")
    @NonFinal
    private String endpoint;

    @Value("${app.callbackUrl}")
    @NonFinal
    private String callBackUrl;

    @Value("${app.redirectedUrl}")
    @NonFinal
    private String redirectUrl;

    public List<BillResponse> getAllBill() {
        return billRepository.findAll().stream().map(billMapper::toBillResponse).toList();
    }

    public BillResponse getBillById(String id) {
        var result = billRepository.findByBillId(id).orElseThrow(() ->
                new AppException(ErrorCode.FIND_BILL_BY_ID_FAILED));

        return billMapper.toBillResponse(result);
    }

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public Object creatBill(String billId) throws Exception {
        UserResponse user = identityClient.getMyInfo().getResult();

        PaymentClientRequest paymentClientRequest = PaymentClientRequest.builder()
                .packageId(billId)
                .userId(user.getId())
                .build();

        EmbedData_Request embedDataRequest = EmbedData_Request.builder()
                .redirecturl(redirectUrl)
                .build();

        final Map<String, Object> items = new HashMap<String, Object>() {{
            put("packageId", paymentClientRequest.getPackageId());
            put("userId", paymentClientRequest.getUserId());
        }};

        final Map<String, Object> embed_data = new HashMap<String, Object>() {{
            put("redirecturl", embedDataRequest.getRedirecturl());
        }};


        int random_id = UUID.randomUUID().hashCode();
        BillResponse billResponse = getBillById(billId);
        Map<String, Object> order = new HashMap<String, Object>() {{
            put("app_id", appId);
            put("app_trans_id", getCurrentTimeString("yyMMdd") + "_" + random_id);
            put("app_time", System.currentTimeMillis());
            put("app_user", "user123");
            put("amount", String.valueOf(billResponse.getPrice()));
            put("description", "Movie Web Tranfer for the order " + random_id);
            put("bank_code", "zalopayapp");
            put("item", new JSONObject(items).toString());
            put("embed_data", new JSONObject(embed_data).toString());

        }};


        String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" + order.get("app_user") + "|" + order.get("amount")
                + "|" + order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key1, data));
        log.info("Order: {}", order);
        return paymentClient.callback(order);
    }

    public void callBack() {
        log.info("Ok:callback");
    }
}
