/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Cart;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.htt.repository.CartRepository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class CartRepositoryImpl implements CartRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;


    @Override
    public Cart getCartById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Cart.class, id);
    }

    @Override
    public void deleteCartById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        Cart c = this.getCartById(id);
        s.delete(c);
    }
}
