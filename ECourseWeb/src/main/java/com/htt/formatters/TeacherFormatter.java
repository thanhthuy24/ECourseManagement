/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.formatters;

import com.htt.pojo.Teacher;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class TeacherFormatter implements Formatter<Teacher> {

    @Override
    public String print(Teacher teacher, Locale locale) {
        return String.valueOf(teacher.getId());
    }

    @Override
    public Teacher parse(String teacherId, Locale locale) throws ParseException {
        Teacher c = new Teacher();
        c.setId(Integer.parseInt(teacherId));

        return c;
    }

}
