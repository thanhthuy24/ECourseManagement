/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Score;

/**
 *
 * @author Admin
 */
public interface ScoreService {
    void addScore(Long assignmentId, Long userId);
    void addScore2(Score score, Long assignmentId, Long userId);
    Score getScoreByUserIdAndAssignment(Long assignmentId, Long userId);
}
