/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Essay;
import com.htt.pojo.Userassignmentdone;
import com.htt.repository.EssayRepository;
import com.htt.repository.UserAssignmentDoneRepository;
import java.util.Date;
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
public class EssayRepositoryImpl implements EssayRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private EssayRepository essayRepo;
    
    @Autowired
    private UserAssignmentDoneRepository userAssignRepo;

    @Override
    public void addEssay(Essay essay, Long assignmentId, Long userId, Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        if (essay.getId() != null) {
            s.update(essay);
        } else {
            
            List<Essay> list
                    = essayRepo.checkEssay(userId, assignmentId, questionId);

            if (!list.isEmpty()) {
                throw new IllegalArgumentException("User had done this assignment!");
            }
            
            List<Userassignmentdone> listDone = userAssignRepo
                    .getAllByUserAndAssignmentId(userId, assignmentId);

            if (listDone.isEmpty()) {
                Userassignmentdone u = new Userassignmentdone();
                u.setCreatedDate(new Date());
                u.setAssignmentId(essay.getAssignmentId());
                u.setUserId(essay.getUserId());
                s.save(u);

            }
            Essay e = new Essay();
            e.setContent(essay.getContent());
            e.setQuestionId(essay.getQuestionId());
            e.setAssignmentId(essay.getAssignmentId());
            e.setUserId(essay.getUserId());
            s.save(e);
        }
    }
    
    @Override
    public List<Essay> checkEssay(Long userId, Long assignmentId, Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        String essay
                = "FROM Essay p "
                + "WHERE p.userId.id = :userId and"
                + " p.assignmentId.id = :assignmentId and"
                + " p.questionId.id = :questionId";
        return s.createQuery(essay)
                .setParameter("userId", userId)
                .setParameter("assignmentId", assignmentId)
                .setParameter("questionId", questionId)
                .list();
    }

    @Override
    public List<Essay> getEssayByUserId(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String assignQuery = "FROM Essay p WHERE p.userId.id = :userId";
        return s.createQuery(assignQuery)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public List<Essay> getEssayByQuestionId(Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        String essay = "FROM Essay p WHERE p.questionId.id = :questionId";
        return s.createQuery(essay)
                .setParameter("questionId", questionId)
                .list();
    }

    @Override
    public List<Essay> getEssayByAssignmentId(Long assignmentId) {
         Session s = this.factory.getObject().getCurrentSession();
        String essay = "FROM Essay p WHERE p.assignmentId.id = :assignmentId";
        return s.createQuery(essay)
                .setParameter("assignmentId", assignmentId)
                .list();
    }

}
