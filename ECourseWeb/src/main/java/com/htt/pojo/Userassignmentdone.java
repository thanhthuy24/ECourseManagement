/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.pojo;

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
@Table(name = "userassignmentdone")
@XmlRootElement
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Userassignmentdone.findAll", query = "SELECT u FROM Userassignmentdone u"),
    @NamedQuery(name = "Userassignmentdone.findById", query = "SELECT u FROM Userassignmentdone u WHERE u.id = :id"),
    @NamedQuery(name = "Userassignmentdone.findByCreatedDate", query = "SELECT u FROM Userassignmentdone u WHERE u.createdDate = :createdDate")})
public class Userassignmentdone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    
    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    @ManyToOne
    private Assignment assignmentId;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

}
