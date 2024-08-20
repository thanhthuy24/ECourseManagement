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
public Videocomplete addVideos(Videocomplete v) {
    Session s = this.factory.getObject().getCurrentSession();
    Videocomplete videoComplete = new Videocomplete();

    // Lấy User hiện tại từ SecurityContextHolder
       User currentUser = this.userRepo.getUserByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName());
    
    if (currentUser == null) {
        // Xử lý khi không tìm thấy User hiện tại
        throw new RuntimeException("User not found");
    }

    // Gán UserId cho videoComplete
    videoComplete.setUserId(currentUser);

    // Gán ngày hoàn thành cho videoComplete
    videoComplete.setCompletedDate(new Date());

    // Lấy Video từ videoRepo
    Video video = this.videoRepo.getVideoById(v.getVideoId().getId());
    
    if (video == null) {
        // Xử lý khi không tìm thấy Video
        throw new RuntimeException("Video not found");
    }

    // Gán VideoId cho videoComplete
    videoComplete.setVideoId(video);

    // Lưu videoComplete vào cơ sở dữ liệu
    s.save(videoComplete);

    return videoComplete;
}

}
