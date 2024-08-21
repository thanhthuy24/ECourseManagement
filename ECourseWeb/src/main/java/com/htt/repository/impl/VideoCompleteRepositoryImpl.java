/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.dto.VideoCompleteDTO;
import com.htt.pojo.User;
import com.htt.pojo.Video;
import com.htt.pojo.Videocomplete;
import com.htt.repository.UserRepository;
import com.htt.repository.VideoCompleteRepository;
import com.htt.repository.VideoRepository;
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
public class VideoCompleteRepositoryImpl implements VideoCompleteRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VideoRepository videoRepo;

    @Override
    public void addVideos(Long userId, Long videoId) {
        Session s = this.factory.getObject().getCurrentSession();
        Videocomplete newVideoComplete = new Videocomplete();
        newVideoComplete.setUserId(this.userRepo.getUserById(userId));
        newVideoComplete.setVideoId(this.videoRepo.getVideoById(videoId));
        newVideoComplete.setCompletedDate(new Date());

        s.save(newVideoComplete);
    }

    @Override
    public List<Videocomplete> getVideosCompleted(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Videocomplete WHERE userId.id = :userId"; // Sửa tên tham số trong HQL
        return s.createQuery(hql, Videocomplete.class)
                .setParameter("userId", userId) // Sửa tên tham số trong setParameter
            .list();
    }

}
