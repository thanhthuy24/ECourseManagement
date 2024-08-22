/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.htt.repository.CartRepository;

/**
 *
 * @author Admin
 */
@Service
public class CartServiceImpl implements CartRepository{
    
    @Autowired
    private CartRepository cartRepo;

    @Override
    public Cart getCartById(Long id) {
        return this.cartRepo.getCartById(id);
    }

    @Override
    public void deleteCartById(Long id) {
        this.cartRepo.deleteCartById(id);
    }
    
}
