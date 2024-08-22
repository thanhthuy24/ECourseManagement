/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Course;
import com.htt.pojo.CourseProcess;
import com.htt.pojo.User;
import com.htt.repository.ProgressRepository;
import java.util.Date;
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
public class ProgressRepositoryImpl implements ProgressRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public float calculateCourseProgress(Long userId, Long courseId) {
        Session s = factory.getObject().getCurrentSession();
        String totalVideosQuery = "SELECT COUNT(v.id) FROM Video v WHERE v.courseId.id = :courseId";
        Long totalVideos = (Long) s.createQuery(totalVideosQuery)
                .setParameter("courseId", courseId)
                .uniqueResult();

        // Fetch number of videos the user has watched in the course
        String watchedVideosQuery = "SELECT COUNT(h.id) FROM Videocomplete h WHERE h.userId.id = :userId AND h.videoId.courseId.id = :courseId";
        Long watchedVideos = (Long) s.createQuery(watchedVideosQuery)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .uniqueResult();

        float progress = 0.0f;
        if (totalVideos > 0) {
            progress = ((float) watchedVideos / totalVideos) * 100;
        }

        // Fetch the Process entity for the user and course, or create a new one if it doesn't exist
        String processQuery = "FROM CourseProcess p WHERE p.userId.id = :userId AND p.courseId.id = :courseId";
        CourseProcess courseProcess = (CourseProcess) s.createQuery(processQuery)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .uniqueResult();

        if (courseProcess == null) {
            courseProcess = new CourseProcess();
            courseProcess.setUserId(s.get(User.class, userId));
            courseProcess.setCourseId(s.get(Course.class, courseId));
        }

        // Update the completionPercentage and status (if applicable)
        courseProcess.setCompletionPercentage(progress);
        courseProcess.setStatus(progress == 100.0f ? "Completed" : "In Progress");
        courseProcess.setUpdatedDate(new Date());
        
        // Save or update the Process entity
        s.saveOrUpdate(courseProcess);

        return progress;
    }

    @Override
    public CourseProcess getProcess(Long userId, Long courseId) {
        Session s = factory.getObject().getCurrentSession();
        String processQuery = "FROM CourseProcess p WHERE p.userId.id = :userId AND p.courseId.id = :courseId";
        CourseProcess courseProcess = (CourseProcess) s.createQuery(processQuery)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .uniqueResult();
        return courseProcess;
    }

}
