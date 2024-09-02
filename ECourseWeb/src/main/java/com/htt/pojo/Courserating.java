/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "courserating")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courserating.findAll", query = "SELECT c FROM Courserating c"),
    @NamedQuery(name = "Courserating.findById", query = "SELECT c FROM Courserating c WHERE c.id = :id"),
    @NamedQuery(name = "Courserating.findByRatingDate", query = "SELECT c FROM Courserating c WHERE c.ratingDate = :ratingDate"),
    @NamedQuery(name = "Courserating.findByRating", query = "SELECT c FROM Courserating c WHERE c.rating = :rating"),
    @NamedQuery(name = "Courserating.findByComment", query = "SELECT c FROM Courserating c WHERE c.comment = :comment")})
public class Courserating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "ratingDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ratingDate;
    @Column(name = "rating")
    private Long rating;
    @Size(max = 255)
    @Column(name = "comment")
    private String comment;
    
    @PrePersist
    protected void onCreate() {
        this.ratingDate = new Date();
    }

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;

}
