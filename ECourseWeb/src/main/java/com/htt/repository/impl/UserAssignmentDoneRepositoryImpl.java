/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Userassignmentdone;
import com.htt.repository.UserAssignmentDoneRepository;
import java.util.List;
import org.hibernate.Session;
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
public class UserAssignmentDoneRepositoryImpl implements UserAssignmentDoneRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Userassignmentdone> getAllByUserAndAssignmentId(Long userId, Long assignmentId) {
        Session s = this.factory.getObject().getCurrentSession();
        String sql = "FROM Userassignmentdone WHERE userId.id = :userId and assignmentId.id = :assignmentId";
        return s.createQuery(sql, Userassignmentdone.class)
                .setParameter("assignmentId", assignmentId)
                .setParameter("userId", userId)
                .list();
    }

}
