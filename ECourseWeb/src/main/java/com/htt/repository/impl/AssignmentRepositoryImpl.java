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
    public List<Assignment> getAssignment(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<Assignment> c = b.createQuery(Assignment.class);

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
    public void addOrUpdate(Assignment c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c); //chen
        }
    }

    @Override
    public Assignment getAssignmentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Assignment.class, id);
    }

    @Override
    public void deleteAssignment(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Assignment c = this.getAssignmentById(id);
        s.delete(c);
    }

}
