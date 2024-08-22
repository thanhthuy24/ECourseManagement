/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Cart;

/**
 *
 * @author Admin
 */
public interface CartRepository {
    Cart getCartById(Long id);
    void deleteCartById(Long id);
}
