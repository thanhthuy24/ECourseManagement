/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Choice;
import com.htt.repository.ChoiceRepository;
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
public class ChoiceRepositoryImpl implements ChoiceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addChoice(Choice choice) {
        Session s = this.factory.getObject().getCurrentSession();
        if (choice.getId() != null) {
            s.update(choice);
        } else {
            Choice c = new Choice();
            c.setContent(choice.getContent());
            c.setIsCorrect(choice.getIsCorrect());
            c.setQuestionId(choice.getQuestionId());

            s.save(c);
        }
    }

    @Override
    public List<Choice> getChoicesByQuestionId(Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Choice WHERE questionId.id = :questionId";
        return s.createQuery(hql, Choice.class)
                .setParameter("questionId", questionId)
                .list();
    }

    @Override
    public List<Choice> getChoicesByCorrectByQuestionId(Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Choice WHERE questionId.id = :questionId and isCorrect = true";
        return s.createQuery(hql, Choice.class)
                .setParameter("questionId", questionId)
                .list();
    }

}
