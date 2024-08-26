/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Question;
import com.htt.repository.QuestionRepository;
import com.htt.service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository quesRepo;
    
    @Override
    public void addOrUpdateQuestions(Question q) {
        this.quesRepo.addOrUpdateQuestions(q);
    }

    @Override
    public List<Question> getQuestionsByAssignmentId(Long assignmentId) {
        return this.quesRepo.getQuestionsByAssignmentId(assignmentId);
    }

    @Override
    public Question getQuestionById(Long id) {
        return this.quesRepo.getQuestionById(id);
    }
}
