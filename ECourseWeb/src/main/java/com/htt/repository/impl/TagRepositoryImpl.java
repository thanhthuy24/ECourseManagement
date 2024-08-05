/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Tag;
import com.htt.repository.TagRepository;
import java.util.List;
import javax.persistence.Query;
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
public class TagRepositoryImpl implements TagRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    

    @Override
    public List<Tag> getTags() {
        Session s = this.factory.getObject().getCurrentSession();
            Query q = s.createQuery("From Tag");
            return q.getResultList();
    }
    
}
