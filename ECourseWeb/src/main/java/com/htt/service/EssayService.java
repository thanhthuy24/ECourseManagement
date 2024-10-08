/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Essay;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface EssayService {
    void addEssay(Essay essay, Long assignmentId, Long userId, Long questionId);
    List<Essay> getEssayByUserId(Long userId);
    List<Essay> getEssayByQuestionId(Long questionId);
    List<Essay> getEssayByAssignmentId(Long assignmentId);
}
