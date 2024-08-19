/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Cart;
import com.htt.pojo.Receipt;
import com.htt.pojo.ReceiptDetail;
import com.htt.repository.CourseRepository;
import com.htt.repository.ReceiptRepository;
import com.htt.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addReceipt(List<Cart> carts) {
        if (carts != null) {
            Session s = this.factory.getObject().getCurrentSession();
            Receipt receipt = new Receipt();
            receipt.setUserId(this.userRepo.getUserByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName()));
            receipt.setCreatedDate(new Date());

           float totalPrice = (float) carts.stream()
                .mapToDouble(c -> c.getPrice() * c.getQuantity())
                .sum();
            receipt.setTotal(totalPrice);

            s.save(receipt);

            for (Cart c : carts) {
                ReceiptDetail d = new ReceiptDetail();
                d.setPrice(c.getPrice());
                d.setQuantity(c.getQuantity());
                d.setCourseId(courseRepo.getCourseById(c.getId()));
                d.setReceiptId(receipt);
                s.save(d);
            }
        }
    }

}
