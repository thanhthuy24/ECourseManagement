/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository;

import com.htt.dto.QuestionDTO;
import com.htt.pojo.Question;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface QuestionRepository {
    void addOrUpdateQuestions(Question q);
    List<Question> getQuestionsByAssignmentId(Long assignmentId);
    Question getQuestionById(Long id);
}
