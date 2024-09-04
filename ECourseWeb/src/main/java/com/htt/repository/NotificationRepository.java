/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Notification;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface NotificationRepository {
    List<Notification> listNotificationByUser(Long userId);
    void createNotification(Notification n);
    List<Notification> getUnreadNotifications(Long userId);
    void markAsRead(Long notificationId);
    Notification getNoticById(Long notificationId);
}
