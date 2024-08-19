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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "receipt_detail")
@XmlRootElement
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "ReceiptDetail.findAll", query = "SELECT r FROM ReceiptDetail r"),
    @NamedQuery(name = "ReceiptDetail.findById", query = "SELECT r FROM ReceiptDetail r WHERE r.id = :id"),
    @NamedQuery(name = "ReceiptDetail.findByQuantity", query = "SELECT r FROM ReceiptDetail r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "ReceiptDetail.findByPrice", query = "SELECT r FROM ReceiptDetail r WHERE r.price = :price"),
    @NamedQuery(name = "ReceiptDetail.findByDiscount", query = "SELECT r FROM ReceiptDetail r WHERE r.discount = :discount")})
public class ReceiptDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private float price;
    @Column(name = "discount")
    private float discount;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    @ManyToOne
    private Receipt receiptId;
    
}
