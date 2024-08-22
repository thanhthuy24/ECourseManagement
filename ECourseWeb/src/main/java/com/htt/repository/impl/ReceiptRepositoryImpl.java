/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.repository.impl;

import com.htt.pojo.Cart;
import com.htt.pojo.Enrollment;
import com.htt.pojo.Receipt;
import com.htt.pojo.ReceiptDetail;
import com.htt.repository.CourseRepository;
import com.htt.repository.EnrollmentRepository;
import com.htt.repository.ReceiptRepository;
import com.htt.repository.UserRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Override
    public void addReceipt(List<Cart> carts) {
        if (carts != null) {
            Session s = this.factory.getObject().getCurrentSession();
            
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Long userId = this.userRepo.getUserByUsername(username).getId();
            
            for (Cart cart : carts) {
            Long courseId = cart.getId();

            // Kiểm tra sự tồn tại của Enrollment
            List<Enrollment> enrollments = enrollmentRepo.getAllEnrollments(userId, courseId);

            if (!enrollments.isEmpty()) {
                throw new IllegalArgumentException("User is not enrolled in course: " + courseId);
                }
            }
            
            Receipt receipt = new Receipt();
            receipt.setUserId(this.userRepo.getUserByUsername(username));
            receipt.setCreatedDate(new Date());

            float totalPrice = (float) carts.stream()
                    .mapToDouble(c -> c.getPrice() * c.getQuantity())
                    .sum();
            receipt.setTotal(totalPrice);

            s.save(receipt);

            for (Cart c : carts) {
                ReceiptDetail d = new ReceiptDetail();
                d.setPrice(c.getPrice());
                d.setQuantity(c.getQuantity());
                d.setCourseId(courseRepo.getCourseById(c.getId()));
                d.setReceiptId(receipt);
                
                Enrollment enrollment = new Enrollment();
                enrollment.setEnrollmentDate(new Date());
                enrollment.setUserId(this.userRepo.getUserByUsername(username));
                enrollment.setCourseId(courseRepo.getCourseById(c.getId()));
                
                s.save(d);
                s.save(enrollment);
            }
            
            
        }
    }

    @Override
    public List<Receipt> getReceiptsByUserId(Long userId) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "FROM Receipt WHERE userId.id = :userId";
        return s.createQuery(hql, Receipt.class)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public Long countByUserId(Long userId) {
        Session session = this.factory.getObject().getCurrentSession();
        // Sử dụng `user_id` như trong câu lệnh SQL
        String sql = "SELECT COUNT(*) FROM Receipt WHERE userId.id = :userId";
        return ((Number) session.createNativeQuery(sql)
                .setParameter(1, userId) // Tham số thứ nhất trong câu lệnh SQL
                .getSingleResult()).longValue();
    }
}
