/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Cart;
import com.htt.pojo.Receipt;
import com.htt.repository.ReceiptRepository;
import com.htt.service.ReceiptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;

    @Override
    public void addReceipt(List<Cart> carts) {
        this.receiptRepo.addReceipt(carts);
    }

    @Override
    public List<Receipt> getReceiptsByUserId(Long userId) {
        return this.receiptRepo.getReceiptsByUserId(userId);
    }        

    @Override
    public Long countByUserId(Long userId) {
        return this.receiptRepo.countByUserId(userId);
    }
        

}
