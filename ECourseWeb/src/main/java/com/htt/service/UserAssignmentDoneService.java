/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Userassignmentdone;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserAssignmentDoneService {
    List<Userassignmentdone> getAllByUserAndAssignmentId(Long userId, Long assignmentId);
}
