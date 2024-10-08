/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.dto.UserDTO;
import com.htt.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface UserService extends UserDetailsService {
    List<User> getUsers();
    User getUserByUsername(String username);
    void addOrUpdate(User c);
    User getUserById(Long id);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params, MultipartFile avatar);
    UserDTO getUserWithEnrollments(Long userId);
    void deleteUser(Long id);
    void updateInfomationUser(User user);
    User getUserByUsername1(String username);
    void addUserGG(String username, String email, String firstName, String lastName, String avatar);
}
