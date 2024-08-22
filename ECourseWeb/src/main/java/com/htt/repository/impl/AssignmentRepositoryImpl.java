/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Assignment;
import com.htt.pojo.Lesson;
import com.htt.repository.AssignmentRepository;
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

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class AssignmentRepositoryImpl implements AssignmentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addOrUpdate(Assignment c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c); //chen
            
        }
    }
    
    @Override
    public Assignment getAssignmentByLessonId(Long lessonId) {
        Session s = this.factory.getObject().getCurrentSession();
        String assignQuery = "FROM Assignment p WHERE p.lessonId.id = :lessonId";
        Assignment assignment = (Assignment) s.createQuery(assignQuery)
                .setParameter("lessonId", lessonId)
                .uniqueResult();
        return assignment;
    }

    @Override
    public Assignment getAssignmentById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Assignment.class, id);
    }

    @Override
    public void deleteAssignment(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        Assignment c = this.getAssignmentById(id);
        s.delete(c);
    }

    @Override
    public List<Assignment> getAssignments() {
        Session s = this.factory.getObject().getCurrentSession();
         Query q = s.createQuery("From Assignment");
        return q.getResultList();
    }
}
