/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Answerchoice;
import com.htt.pojo.Question;
import com.htt.pojo.Score;
import com.htt.repository.AnswerchoiceRepository;
import com.htt.repository.AssignmentRepository;
import com.htt.repository.QuestionRepository;
import com.htt.repository.ScoreRepository;
import com.htt.repository.UserRepository;
import java.util.List;
import javax.persistence.NoResultException;
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
public class ScoreRepositoryImpl implements ScoreRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private AnswerchoiceRepository answerChoiceRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Autowired
    private QuestionRepository quesRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @Override
    public void addScore(Long assignmentId, Long userId) {
        Session s = this.factory.getObject().getCurrentSession();

        Score sc = scoreRepo.getScoreByUserIdAndAssignment(assignmentId, userId);

        if (sc != null) {
            throw new IllegalArgumentException("User had done assignment!");
        }

        List<Answerchoice> answerChoices
                = answerChoiceRepo.checkAnswer(userId, assignmentId);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Question> totalQuestions
                = quesRepo.getQuestionsByAssignmentId(assignmentId);

        long score = 0;
        for (Answerchoice answer : answerChoices) {
            if (answer.getChoiceId().getIsCorrect()) {
                score++;
            }
        }

        double percentage = ((double) score / totalQuestions.size()) * 100;

        String feedback;
        if (percentage >= 80) {
            feedback = "Bravo!";
        } else if (percentage >= 50) {
            feedback = "Good!";
        } else {
            feedback = "You need to be more careful!";
        }

        Score userScore = new Score();
        userScore.setUserId(this.userRepo.getUserByUsername(username));  
        userScore.setAssignmentId(this.assignmentRepo.getAssignmentById(assignmentId)); 
        userScore.setScore(score);
        userScore.setFeedBack(feedback);

        s.save(userScore);
//        scoreRepo.addScore(assignmentId, userId);
    }

    @Override
    public void addScoreEssay(Score score, Long assignmentId, Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        if (score.getId() != null) {
            s.update(score);
        } else {
            Score sc = scoreRepo.getScoreByUserIdAndAssignment(assignmentId, userId);

            if (sc != null) {
                throw new IllegalArgumentException("User had done assignment!");
            }
            
            Score userScore = new Score();
            userScore.setUserId(score.getUserId()); 
            userScore.setAssignmentId(this.assignmentRepo.getAssignmentById(assignmentId));  // Giả sử Assignment có constructor Assignment(Long id)
            userScore.setScore(score.getScore());
            userScore.setFeedBack(score.getFeedBack());

            s.save(userScore);
        }
    }

    @Override
    public Score getScoreByUserIdAndAssignment(Long assignmentId, Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String sql = "FROM Score WHERE userId.id = :userId and assignmentId.id = :assignmentId";
        try {
            return s.createQuery(sql, Score.class)
                    .setParameter("assignmentId", assignmentId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
