/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Video;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface VideoService {
    List<Video> getVideos(Map<String, String> params);

    List<Video> getVideos();

    void addOrUpdate(Video c);

    List<Video> getVideoByLessonId(Long id);
    
    Video getVideoById(Long id);

    void deleteVideo(Long id);
} 
