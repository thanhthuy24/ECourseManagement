/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Cart;
import com.htt.pojo.Receipt;
import com.htt.service.ReceiptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ApiReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.CREATED)
    public void pay(@RequestBody List<Cart> carts) {
        this.receiptService.addReceipt(carts);
    }

    @GetMapping("/receipts/user/{userId}")
    public ResponseEntity<?> getReceiptByUserId(
            @PathVariable("userId") Long userId) {

        return ResponseEntity.ok(receiptService.getReceiptsByUserId(userId));
    }

    @GetMapping("/receipts/count/{userId}")
    public ResponseEntity<?> countReceipt(
            @PathVariable("userId") Long userId) {
        Long count = receiptService.countByUserId(userId);
        return ResponseEntity.ok(count);
    }
}
