/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "recepit_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecepitDetail.findAll", query = "SELECT r FROM RecepitDetail r"),
    @NamedQuery(name = "RecepitDetail.findById", query = "SELECT r FROM RecepitDetail r WHERE r.id = :id"),
    @NamedQuery(name = "RecepitDetail.findByQuantity", query = "SELECT r FROM RecepitDetail r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "RecepitDetail.findByPrice", query = "SELECT r FROM RecepitDetail r WHERE r.price = :price"),
    @NamedQuery(name = "RecepitDetail.findByDiscount", query = "SELECT r FROM RecepitDetail r WHERE r.discount = :discount")})
public class RecepitDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Long price;
    @Column(name = "discount")
    private Long discount;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    @ManyToOne
    private Receipt receiptId;

    public RecepitDetail() {
    }

    public RecepitDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Receipt getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Receipt receiptId) {
        this.receiptId = receiptId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecepitDetail)) {
            return false;
        }
        RecepitDetail other = (RecepitDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.htt.pojo.RecepitDetail[ id=" + id + " ]";
    }
    
}
