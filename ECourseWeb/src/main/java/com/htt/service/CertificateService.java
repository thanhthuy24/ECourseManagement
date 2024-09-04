/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Course;
import com.htt.pojo.User;

/**
 *
 * @author Admin
 */
public interface CertificateService {
     String createCertificate(Long userId, Long courseId);

    String createCertificatePDF(User user, Course course, Long certificateId);
}
