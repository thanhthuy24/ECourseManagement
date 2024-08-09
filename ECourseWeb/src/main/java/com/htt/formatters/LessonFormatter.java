/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.formatters;

import com.htt.pojo.Lesson;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class LessonFormatter implements Formatter<Lesson>{

    @Override
    public String print(Lesson lesson, Locale locale) {
        return String.valueOf(lesson.getId());
    }

    @Override
    public Lesson parse(String lessonId, Locale locale) throws ParseException {
        Lesson c = new Lesson();
        c.setId(Integer.parseInt(lessonId));
        
        return c;
    }
    
}
