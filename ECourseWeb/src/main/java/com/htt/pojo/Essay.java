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
import javax.validation.constraints.Size;
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
@Table(name = "essay")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Essay.findAll", query = "SELECT e FROM Essay e"),
    @NamedQuery(name = "Essay.findById", query = "SELECT e FROM Essay e WHERE e.id = :id"),
    @NamedQuery(name = "Essay.findByContent", query = "SELECT e FROM Essay e WHERE e.content = :content")})
public class Essay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "content")
    private String content;
    
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne
    private Question questionId;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

}
