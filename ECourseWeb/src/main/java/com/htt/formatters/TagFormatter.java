/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.formatters;

import com.htt.pojo.Tag;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class TagFormatter implements Formatter<Tag> {

    @Override
    public String print(Tag tag, Locale locale) {
        return String.valueOf(tag.getId());
    }

    @Override
    public Tag parse(String tagId, Locale locale) throws ParseException {
        Tag c = new Tag();
        c.setId(Integer.parseInt(tagId));

        return c;
    }
}
