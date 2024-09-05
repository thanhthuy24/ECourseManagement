/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
@Table(name = "assignment")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignment.findAll", query = "SELECT a FROM Assignment a"),
    @NamedQuery(name = "Assignment.findById", query = "SELECT a FROM Assignment a WHERE a.id = :id"),
    @NamedQuery(name = "Assignment.findByName", query = "SELECT a FROM Assignment a WHERE a.name = :name"),
    @NamedQuery(name = "Assignment.findByCreatedDate", query = "SELECT a FROM Assignment a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "Assignment.findByDueDate", query = "SELECT a FROM Assignment a WHERE a.dueDate = :dueDate"),
    
})
public class Assignment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @ManyToOne
    private Tag tagId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne
    private Lesson lessonId;
    
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        
    }

}
