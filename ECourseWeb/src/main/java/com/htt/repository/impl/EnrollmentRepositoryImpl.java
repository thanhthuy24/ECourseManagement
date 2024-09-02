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
    public List<Enrollment> getAllEnrollments(Long userId, Long courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Enrollment WHERE userId.id = :userId AND courseId.id = :courseId";
        return s.createQuery(hql, Enrollment.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .list();
    }

    @Override
    public List<Enrollment> getEnrollmentByUserId(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Enrollment WHERE userId.id = :userId";
        return s.createQuery(hql, Enrollment.class)
                .setParameter("userId", id)
                .list();
    }
    
    @Override
    public Long countByCourseId(Long courseId) {
        Session session = this.factory.getObject().getCurrentSession();
        // Sử dụng `user_id` như trong câu lệnh SQL
        String sql = "SELECT COUNT(*) FROM enrollment WHERE course_id = :courseId";
        return ((Number) session.createNativeQuery(sql)
                .setParameter("courseId", courseId) // Tham số thứ nhất trong câu lệnh SQL
                .getSingleResult()).longValue();
    }
    
    
}
