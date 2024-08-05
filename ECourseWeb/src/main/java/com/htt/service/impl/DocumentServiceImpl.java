/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Document;
import com.htt.service.DocumentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DocumentServiceImpl implements DocumentService{
    
    @Autowired
    private DocumentService documentSer;

    @Override
    public List<Document> getDocuments(Map<String, String> params) {
        return this.documentSer.getDocuments(params);
    }

    @Override
    public void addOrUpdate(Document c) {
        this.documentSer.addOrUpdate(c);
    }

    @Override
    public Document getDocumentById(int id) {
        return this.documentSer.getDocumentById(id);
    }

    @Override
    public void deleteDocument(int id) {
        this.documentSer.deleteDocument(id);
    }
}
