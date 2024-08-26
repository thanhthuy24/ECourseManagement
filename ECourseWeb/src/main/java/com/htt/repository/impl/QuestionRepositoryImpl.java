/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Assignment;
import com.htt.pojo.Question;
import com.htt.pojo.Tag;
import com.htt.repository.AssignmentRepository;
import com.htt.repository.QuestionRepository;
import com.htt.repository.TagRepository;
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
public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Autowired
    private TagRepository tagRepo;

    @Override
    public void addOrUpdateQuestions(Question q) {
        Session s = this.factory.getObject().getCurrentSession();
        if (q.getId() != null) {
            s.update(q);
        } else {
            Question question = new Question();
            question.setName(q.getName());
            question.setAssignmentId(q.getAssignmentId());
            question.setTagId(q.getTagId());

            s.save(question);
        }
    }

    @Override
    public List<Question> getQuestionsByAssignmentId(Long assignmentId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Question WHERE assignmentId.id = :assignmentId";
        return s.createQuery(hql, Question.class)
                .setParameter("assignmentId", assignmentId)
                .list();
    }

    @Override
    public Question getQuestionById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Question.class, id);
    }

}
