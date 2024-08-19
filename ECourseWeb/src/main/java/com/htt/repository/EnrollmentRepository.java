/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Enrollment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface EnrollmentRepository {
    List<Enrollment> getAllEnrollments();
    List<Enrollment> getEnrollmentByUserId(Long id);
    public List<Enrollment> getEnrollments(Map<String, String> params);
}
