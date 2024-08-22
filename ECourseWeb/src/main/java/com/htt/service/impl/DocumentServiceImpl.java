/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.htt.pojo.Document;
import com.htt.repository.DocumentRepository;
import com.htt.service.DocumentService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DocumentServiceImpl implements DocumentService{
    
    @Autowired
    private DocumentRepository documentRepo;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public void addOrUpdate(Document c) {
        if (!c.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                c.setName(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CourseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.documentRepo.addOrUpdate(c);
    }
    
    @Override
    public Document getDocumentByLessonId(Long lessonId) {
        return this.documentRepo.getDocumentById(lessonId);
    }

    @Override
    public Document getDocumentById(Long id) {
        return this.documentRepo.getDocumentById(id);
    }

    @Override
    public void deleteDocument(Long id) {
        this.documentRepo.deleteDocument(id);
    }
}
