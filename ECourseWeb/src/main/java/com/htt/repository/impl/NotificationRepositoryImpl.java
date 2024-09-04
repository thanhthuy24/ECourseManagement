/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Notification;
import com.htt.repository.NotificationRepository;
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
public class NotificationRepositoryImpl implements NotificationRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Notification> listNotificationByUser(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Notification WHERE userId.id = :userId";
        return s.createQuery(hql, Notification.class)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public void createNotification(Notification n) {
        Session s = this.factory.getObject().getCurrentSession();
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Notification notification = new Notification();
        notification.setUserId(n.getUserId());
        notification.setTitle(n.getTitle());
        notification.setMessage(n.getMessage());
        notification.setCreatedDate(new Date());
        notification.setIsRead(false);
        s.save(notification);
    }
    
    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Notification WHERE userId.id = :userId and isRead = false";
        return s.createQuery(hql, Notification.class)
                .setParameter("userId", userId)
                .list();
    }
    
    @Override
    public Notification getNoticById(Long notificationId){
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Notification.class, notificationId);
    }
    
    @Override
    public void markAsRead(Long notificationId) {
        Session s = this.factory.getObject().getCurrentSession();
        Notification notic = this.getNoticById(notificationId);
        notic.setIsRead(true);
        s.save(notic);
    }
    
}
