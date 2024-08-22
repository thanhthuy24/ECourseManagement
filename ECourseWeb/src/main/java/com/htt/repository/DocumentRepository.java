/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Document;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DocumentRepository {
    void addOrUpdate(Document c);
    Document getDocumentByLessonId(Long lessonId);
    Document getDocumentById(Long id);
    void deleteDocument(Long id);
}
