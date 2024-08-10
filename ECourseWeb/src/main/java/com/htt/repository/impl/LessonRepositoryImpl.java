/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;
import com.htt.pojo.Lesson;
import com.htt.repository.LessonRepository;
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
public class LessonRepositoryImpl implements LessonRepository{
    
     private static final int PAGE_SIZE = 10;
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Lesson> getLessons(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        
        CriteriaQuery<Lesson> c = b.createQuery(Lesson.class);
        
        Root root = c.from(Lesson.class);
        c.select(root);
        
        if(params != null){
            List<Predicate> predicates = new ArrayList<>();
            
            String kw = params.get("q");
            if(kw != null && !kw.isEmpty()){
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }
            
            String courseId = params.get("courseId");
            if (courseId != null && !courseId.isEmpty()) {
                Predicate p4 = b.equal(root.get("courseId"), Integer.parseInt(courseId));
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
    public void addOrUpdate(Lesson c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c); //chen
        }
        c.setIsActive(true);
    }

    @Override
    public Lesson getLessonById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Lesson.class, id);
    }

    @Override
    public void deleteLesson(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Lesson c = this.getLessonById(id);
        s.delete(c);
    }

    @Override
    public List<Lesson> getLessons() {
       Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Lesson");
        return q.getResultList();
    }
    
}
