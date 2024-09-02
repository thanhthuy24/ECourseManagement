/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Course;
import com.htt.pojo.Courserating;
import com.htt.repository.CourseRatingRepository;
import com.htt.repository.CourseRepository;
import com.htt.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class CourseRatingRepositoryImpl implements CourseRatingRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CourseRatingRepository courseRatingRepo;

    @Override
    public void addRating(Courserating c, Long courseId) {
        Session s = this.factory.getObject().getCurrentSession();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Courserating> list
                = courseRatingRepo.checkCourseRating(this.userRepo.getUserByUsername(username).getId(), courseId);

        if (!list.isEmpty()) {
            throw new IllegalArgumentException("User had estimated this course!");
        }

        Courserating courseRating = new Courserating();
        courseRating.setId(c.getId());
        courseRating.setComment(c.getComment());
        courseRating.setRating(c.getRating());
        courseRating.setRatingDate(new Date());
        courseRating.setCourseId(c.getCourseId());
        courseRating.setUserId(this.userRepo.getUserByUsername(username));

        s.save(courseRating);
    }

    @Override
    public List<Courserating> getCourseRatingByUserId(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String rating = "FROM Courserating p WHERE p.userId.id = :userId";
        return s.createQuery(rating)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public List<Courserating> checkCourseRating(Long userId, Long courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        String courseRating
                = "FROM Courserating p "
                + "WHERE p.userId.id = :userId and"
                + " p.courseId.id = :courseId";
        return s.createQuery(courseRating)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .list();
    }

    @Override
    public List<Courserating> getCourseRatingByCourseId(Long courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        String rating = "FROM Courserating p WHERE p.courseId.id = :courseId";
        return s.createQuery(rating)
                .setParameter("courseId", courseId)
                .list();
    }

}
