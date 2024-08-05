/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Assignment;
import com.htt.repository.AssignmentRepository;
import com.htt.service.AssignmentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AssignmentServiceImpl implements AssignmentService{
    
    @Autowired
    private AssignmentRepository assignmentRepo;

    @Override
    public List<Assignment> getAssignment(Map<String, String> params) {
        return this.assignmentRepo.getAssignment(params);
    }

    @Override
    public void addOrUpdate(Assignment c) {
        this.assignmentRepo.addOrUpdate(c);
    }

    @Override
    public Assignment getAssignmentById(int id) {
        return this.assignmentRepo.getAssignmentById(id);
    }

    @Override
    public void deleteAssignment(int id) {
         this.assignmentRepo.deleteAssignment(id);
    }
    
}
