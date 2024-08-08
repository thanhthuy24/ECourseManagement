/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Video;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface VideoRepository {

    List<Video> getVideos(Map<String, String> params);

    List<Video> getVideos();

    void addOrUpdate(Video c);

    Video getVideoById(int id);

    void deleteVideo(int id);
}
