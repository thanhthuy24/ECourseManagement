/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.service.ReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiReceiptDetailController {
    @Autowired
    private ReceiptDetailService receiptDetailService;
    
    @GetMapping("/receipt/{receiptId}")
     public ResponseEntity<?> getReceipt(
        @PathVariable("receiptId") Long receiptId){
         try {
              return ResponseEntity.ok(receiptDetailService.getByReceiptId(receiptId));
         } catch(Exception ex){
             return ResponseEntity.badRequest().body(ex.getMessage());
         }
        
     }
}
