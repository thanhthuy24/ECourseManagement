/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Choice;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChoiceService {
    void addChoice(Choice choice);
    List<Choice> getChoicesByQuestionId(Long questionId);
}
