/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.User;
import com.htt.repository.UserRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From User");
        return q.getResultList();
    }

    @Override
    public void addOrUpdate(User c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId() != null) {
            s.update(c);
        } else {
            s.save(c); //chen
            c.setCreatedDate(new Date());
            c.setIsActive(true);
        }

    }

    @Override
    public User getUserById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
        u.setIsActive(true);
        return u;
    }

    @Override
    public User findByIdWithEnrollments(Long userId) {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "SELECT u FROM User u LEFT JOIN FETCH u.enrollments WHERE u.id = :userId";
        org.hibernate.query.Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("userId", userId);
        return query.uniqueResult();
    }

    @Override
    public void deleteUser(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        User user = this.getUserById(id);

        user.setIsActive(false);
    }

    @Override
    public void updateInfomationUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();

        User existingUser = s.get(User.class, user.getId());

        if (existingUser != null) {
            // Cập nhật thông tin của đối tượng hiện tại
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setUsername(user.getUsername());
            existingUser.setAvatar(user.getAvatar());

            // Lưu đối tượng đã cập nhật
            s.update(existingUser);
        } else {
            throw new IllegalArgumentException("User with ID " + user.getId() + " not found.");
        }

    }
    
    @Override
    public User getUserByUsername1(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);

        // Sử dụng getResultList để tránh ném ngoại lệ khi không có kết quả
        List<User> results = q.getResultList();

        // Kiểm tra nếu danh sách trống
        if (!results.isEmpty()) {
            return results.get(0); // Trả về user đầu tiên nếu tìm thấy
        }
        return null; // Trả về null nếu không có kết quả
    }
    
     @Override
    public void addUserGG(String username, String email, String firstName, String lastName, String avatar) {
        Session s = this.factory.getObject().getCurrentSession();
        // Kiểm tra xem người dùng đã tồn tại dựa trên email
        User existingUser = getUserByUsername1(username);
        if (existingUser == null) {
            // Tạo người dùng mới nếu không tồn tại
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setRole("ROLE_USER");
            newUser.setCreatedDate(new Date());
            newUser.setIsActive(true);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPassword("000000");
            newUser.setAvatar(avatar);
            s.save(newUser);
        } else {
            System.out.println("User already exists with email: " + email);
        }
    }
}
