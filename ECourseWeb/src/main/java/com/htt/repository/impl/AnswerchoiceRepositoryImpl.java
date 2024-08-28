/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Answerchoice;
import com.htt.pojo.Userassignmentdone;
import com.htt.repository.AnswerchoiceRepository;
import com.htt.repository.ScoreRepository;
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
public class AnswerchoiceRepositoryImpl implements AnswerchoiceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private AnswerchoiceRepository answerChoiceRepo;

    @Autowired
    private UserAssignmentDoneRepository userAssignRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @Override
    public void addAnswerChoice(Answerchoice answerchoice, Long assignmentId, Long userId, Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        if (answerchoice.getId() != null) {
            s.update(answerchoice);
        } else {
            List<Answerchoice> list
                    = answerChoiceRepo.checkAnswers(userId, assignmentId, questionId);

            if (!list.isEmpty()) {
                throw new IllegalArgumentException("User had done this assignment!");
            }

            List<Userassignmentdone> listDone = userAssignRepo
                    .getAllByUserAndAssignmentId(userId, assignmentId);

            if (listDone.isEmpty()) {
                Userassignmentdone u = new Userassignmentdone();
                u.setCreatedDate(new Date());
                u.setAssignmentId(answerchoice.getAssignmentId());
                u.setUserId(answerchoice.getUserId());
                s.save(u);

            }
            
            s.save(answerchoice);
//            scoreRepo.addScore(assignmentId, userId);
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

    @Override
    public List<Answerchoice> checkAnswers(Long userId, Long assignmentId, Long questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        String answerChoice
                = "FROM Answerchoice p "
                + "WHERE p.userId.id = :userId and"
                + " p.assignmentId.id = :assignmentId and"
                + " p.questionId.id = :questionId";
        return s.createQuery(answerChoice)
                .setParameter("userId", userId)
                .setParameter("assignmentId", assignmentId)
                .setParameter("questionId", questionId)
                .list();
    }

    @Override
    public List<Answerchoice> checkAnswer(Long userId, Long assignmentId) {
        Session s = this.factory.getObject().getCurrentSession();
        String answerChoice
                = "FROM Answerchoice p "
                + "WHERE p.userId.id = :userId and"
                + " p.assignmentId.id = :assignmentId";
        return s.createQuery(answerChoice)
                .setParameter("userId", userId)
                .setParameter("assignmentId", assignmentId)
                .list();
    }
}
