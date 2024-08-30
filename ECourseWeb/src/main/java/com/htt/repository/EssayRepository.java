/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Essay;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface EssayRepository {
    void addEssay(Essay essay);
    List<Essay> getEssayByUserId(Long userId);
    List<Essay> getEssayByQuestionId(Long questionId);
    List<Essay> getEssayByAssignmentId(Long assignmentId);
}
