/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Document;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DocumentService {
    List<Document> getDocuments(Map<String, String> params);
    void addOrUpdate(Document c);
    Document getDocumentById(int id);
    void deleteDocument(int id);
}
