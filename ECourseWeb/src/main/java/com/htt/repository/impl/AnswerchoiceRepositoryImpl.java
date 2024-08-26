/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Answerchoice;
import com.htt.repository.AnswerchoiceRepository;
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
public class AnswerchoiceRepositoryImpl implements AnswerchoiceRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addAnswerChoice(Answerchoice answerchoice) {
        Session s = this.factory.getObject().getCurrentSession();
        if (answerchoice.getId() != null) {
            s.update(answerchoice);
        } else {
            s.save(answerchoice); //chen
            
        }
        
    }

    @Override
    public List<Answerchoice> getAnswerchoiceByUserId(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String answerChoice = "FROM Answerchoice p WHERE p.userId.id = :userId";
        return s.createQuery(answerChoice)
                .setParameter("userId", userId)
                .list();
    }    
}
