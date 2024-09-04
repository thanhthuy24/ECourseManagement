/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Course;
import com.htt.pojo.User;
import com.htt.repository.CertificateRepository;
import com.htt.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificationRepo;

    @Override
    public String createCertificate(Long userId, Long courseId) {
        return this.certificationRepo.createCertificate(userId, courseId);
    }

    @Override
    public String createCertificatePDF(User user, Course course, Long certificateId) {
        return this.certificationRepo.createCertificatePDF(user, course, certificateId);
    }

}
