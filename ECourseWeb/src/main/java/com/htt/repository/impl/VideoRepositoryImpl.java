/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Course;
import com.htt.pojo.Video;
import com.htt.repository.VideoRepository;
import java.util.ArrayList;
import java.util.Date;
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
public class VideoRepositoryImpl implements VideoRepository {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Video> getVideos(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<Video> c = b.createQuery(Video.class);

        Root root = c.from(Video.class);
        c.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }
            
            String lessonId = params.get("lessonId");
            if (lessonId != null && !lessonId.isEmpty()) {
                Predicate p4 = b.equal(root.get("lessonId"), Integer.parseInt(lessonId));
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
    public List<Video> getVideos() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Video");
        return q.getResultList();
    }

    @Override
    public void addOrUpdate(Video c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c); //chen
        }
    }

    @Override
    public Video getVideoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Video.class, id);
    }

    @Override
    public void deleteVideo(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Video c = this.getVideoById(id);
        s.delete(c);
    }

}
