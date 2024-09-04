/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.components.MomoService;
import com.htt.pojo.Cart;
import com.htt.service.ReceiptService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiPaymentController {

    @Autowired
    private MomoService momoService;
    @Autowired
    private ReceiptService receiptService;  

    @PostMapping("/create-payment")
    public String createPayment(@RequestBody Map<String, String> params ) throws Exception {
        
//        this.receiptService.addReceipt(carts);
        
        String orderId = params.get("orderId");
        String amount = params.get("amount");
        String returnUrl = params.get("returnUrl");
        
        if (orderId == null || amount == null || returnUrl == null) {
            throw new IllegalArgumentException("Missing required parameters");
        }

        float amountValue;
        try {
            amountValue = Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount format", e);
        }

        return this.momoService.createPaymentRequest(orderId, amountValue, returnUrl);
    }
    
    @PostMapping("/update-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void pay(@RequestBody List<Cart> carts){
        this.receiptService.addReceipt(carts);
    }
}
