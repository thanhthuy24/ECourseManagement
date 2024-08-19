/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Course;
import com.htt.pojo.Enrollment;
import com.htt.repository.EnrollmentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Enrollment> getEnrollments(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<Enrollment> c = b.createQuery(Enrollment.class);

        Root root = c.from(Enrollment.class);
        c.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String userId = params.get("userId");
            if (userId != null && !userId.isEmpty()) {
                Predicate p4 = b.equal(root.get("userId"), Integer.parseInt(userId));
                predicates.add(p4);
            }

            c.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(c);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int start = (p - 1) * PAGE_SIZE;

                query.setFirstResult(start);
                query.setMaxResults(PAGE_SIZE);
            }
        }
        return query.getResultList();
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Enrollment");
        return q.getResultList();
    }

    @Override
    public List<Enrollment> getEnrollmentByUserId(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findById");
        q.setParameter("id", id);

        return q.getResultList();
    }
    
}
