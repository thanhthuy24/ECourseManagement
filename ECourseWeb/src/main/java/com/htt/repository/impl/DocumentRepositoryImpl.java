/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Document;
import com.htt.pojo.Lesson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.htt.repository.DocumentRepository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class DocumentRepositoryImpl implements DocumentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addOrUpdate(Document c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c); //chen
        }
    }

    @Override
    public Document getDocumentByLessonId(Long lessonId) {
        Session s = this.factory.getObject().getCurrentSession();
        String documentQuery = "FROM Document p WHERE p.lessonId.id = :lessonId";
        Document ducument = (Document) s.createQuery(documentQuery)
                .setParameter("lessonId", lessonId)
                .uniqueResult();
        return ducument;
    }
    
    @Override
    public Document getDocumentById(Long id){
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Document.class, id);
    }

    @Override
    public void deleteDocument(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        Document c = this.getDocumentById(id);
        s.delete(c);
    }

}
