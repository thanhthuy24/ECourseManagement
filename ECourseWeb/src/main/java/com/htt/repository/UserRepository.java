/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserRepository {

    List<User> getUsers();

    User getUserByUsername(String username);

    void addOrUpdate(User c);

    User getUserById(Long id);

    boolean authUser(String username, String password);

    User addUser(User user);

    User findByIdWithEnrollments(Long userId);
    
    void deleteUser(Long id);
    
    void updateInfomationUser(User user);
    
    User getUserByUsername1(String username);
    
    void addUserGG(String username, String email, String firstName, String lastName, String avatar);

}
