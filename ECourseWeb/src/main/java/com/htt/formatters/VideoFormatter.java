/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.formatters;

import com.htt.pojo.Teacher;
import com.htt.pojo.Video;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class VideoFormatter implements Formatter<Video>{

    @Override
    public String print(Video video, Locale locale) {
        return String.valueOf(video.getId());
    }

    @Override
    public Video parse(String videoId, Locale locale) throws ParseException {
        Video c = new Video();
        c.setId(Integer.parseInt(videoId));

        return c;
    }
    
}
