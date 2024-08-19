/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.ReceiptDetail;
import com.htt.repository.CourseRepository;
import com.htt.repository.ReceiptDetailRepository;
import com.htt.repository.UserRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ReceiptDetailRepositoryImpl implements ReceiptDetailRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ReceiptDetail> getByReceiptId(Long receiptId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM ReceiptDetail rd WHERE rd.receiptId.id = :receiptId";
        Query<ReceiptDetail> query = s.createQuery(hql, ReceiptDetail.class);
        query.setParameter("receiptId", receiptId);
        return query.getResultList();
    }

}
