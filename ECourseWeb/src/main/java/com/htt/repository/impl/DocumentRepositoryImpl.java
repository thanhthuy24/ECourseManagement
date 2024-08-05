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
    public List<Document> getDocuments(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<Document> c = b.createQuery(Document.class);

        Root root = c.from(Lesson.class);
        c.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            c.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(c);

        return query.getResultList();
    }

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
    public Document getDocumentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Document.class, id);
    }

    @Override
    public void deleteDocument(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Document c = this.getDocumentById(id);
        s.delete(c);
    }

}
