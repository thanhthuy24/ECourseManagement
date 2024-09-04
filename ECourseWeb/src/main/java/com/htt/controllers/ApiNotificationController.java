/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Notification;
import com.htt.service.NotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiNotificationController {
    @Autowired
    private NotificationService notiSer;
    
    @PostMapping("/notifications")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnswerChoice(
            @RequestBody Notification notic           
    ){
        this.notiSer.createNotification(notic);
    }
    
    @GetMapping("/notifications/{userId}")
    public List<Notification> getUnreadNotifications(
            @PathVariable(value = "userId") Long userId
    ) {
        return notiSer.getUnreadNotifications(userId);
    }

    @PostMapping("/notifications/mark-as-read/{notificationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void markAsRead(
            @PathVariable(value = "notificationId") Long notificationId
    ) {
        notiSer.markAsRead(notificationId);
    }
}
