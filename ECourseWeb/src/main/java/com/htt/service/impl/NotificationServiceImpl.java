/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Notification;
import com.htt.repository.NotificationRepository;
import com.htt.service.NotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class NotificationServiceImpl implements NotificationService{
    
    @Autowired
    private NotificationRepository notificationRepo;

    @Override
    public List<Notification> listNotificationByUser(Long userId) {
        return this.notificationRepo.listNotificationByUser(userId);
    }

    @Override
    public void createNotification(Notification n) {
        this.notificationRepo.createNotification(n);
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return this.notificationRepo.getUnreadNotifications(userId);
    }

    @Override
    public void markAsRead(Long notificationId) {
        this.notificationRepo.markAsRead(notificationId);
    }

    @Override
    public Notification getNoticById(Long notificationId) {
        return this.notificationRepo.getNoticById(notificationId);
    }
    
}
