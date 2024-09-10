/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Certification;
import com.htt.pojo.Course;
import com.htt.pojo.User;
import com.htt.repository.CertificateRepository;
import com.htt.repository.CourseRepository;
import com.htt.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class CertificateRepositoryImpl implements CertificateRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public String createCertificate(Long userId, Long courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        User user = userRepo.getUserById(userId);
        Course course = courseRepo.getCourseById(courseId);
        Certification certificate = new Certification();
        certificate.setUserId(user);
        certificate.setCourseId(course);
        certificate.setIssuanceDate(new Date());
        certificate.setName("Chứng nhận hoàn thành khóa học" + " " + course.getName());
        s.save(certificate);
        return createCertificatePDF(user, course, certificate.getId());
    }

    @Override
    public String createCertificatePDF(User user, Course course, Long certificateId) {

        Document document = new Document();
        String directoryPath = "D:/apps/apache-tomcat-9.0.91/webapps/ROOT/certificates/";
        String pdfPath = directoryPath + certificateId + "_" + user.getId() + "_" + course.getId() + ".pdf";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                Logger.getLogger(CertificateRepositoryImpl.class.getName()).log(Level.SEVERE, "Failed to create directory: " + directoryPath);
                return null;
            }
        }
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            document.add(new Paragraph("Certificate" + " " + course.getName()));
            document.add(new Paragraph("Student: " + user.getFirstName() + " " + user.getLastName()));
            document.add(new Paragraph("Course name: " + course.getName()));
            document.add(new Paragraph("Issuance date: " + LocalDate.now()));
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(CertificateRepositoryImpl.class.getName()).log(Level.SEVERE, "Error creating PDF: " + ex.getMessage(), ex);
            return null;
        }

        return "/certificates/" + certificateId + "_" + user.getId() + "_" + course.getId() + ".pdf";
    }

}
