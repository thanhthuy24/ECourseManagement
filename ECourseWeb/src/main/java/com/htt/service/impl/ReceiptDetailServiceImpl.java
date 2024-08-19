/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.ReceiptDetail;
import com.htt.repository.ReceiptDetailRepository;
import com.htt.service.ReceiptDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReceiptDetailServiceImpl implements ReceiptDetailService{
    
    @Autowired
    private ReceiptDetailRepository receiptdetailRepo;

    @Override
    public List<ReceiptDetail> getByReceiptId(Long receiptId) {
        return this.receiptdetailRepo.getByReceiptId(receiptId);
    }
    
}
