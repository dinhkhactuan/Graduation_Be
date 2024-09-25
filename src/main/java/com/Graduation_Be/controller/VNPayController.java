package com.Graduation_Be.controller;

import com.Graduation_Be.service.impl.VNPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private VNPayServiceImpl vnPayService;

    @PostMapping("/create-payment")
    public ResponseEntity<String> createPayment(@RequestParam long amount, @RequestParam String orderInfo) {
        try {
            String paymentUrl = vnPayService.createPayment(amount, orderInfo);
            return ResponseEntity.ok(paymentUrl);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment URL");
        }
    }

    @GetMapping("/payment-callback")
    public ResponseEntity<String> paymentCallback(@RequestParam Map<String, String> queryParams) {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        if ("00".equals(vnp_ResponseCode)) {
            // Xử lý thanh toán thành công
            return ResponseEntity.ok("successful");
        } else {
            // Xử lý thanh toán thất bại
            return ResponseEntity.ok("failed");
        }
    }
}