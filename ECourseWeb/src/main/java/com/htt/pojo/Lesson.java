/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "lesson")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lesson.findAll", query = "SELECT l FROM Lesson l"),
    @NamedQuery(name = "Lesson.findById", query = "SELECT l FROM Lesson l WHERE l.id = :id"),
    @NamedQuery(name = "Lesson.findByName", query = "SELECT l FROM Lesson l WHERE l.name = :name"),
    @NamedQuery(name = "Lesson.findByDescription", query = "SELECT l FROM Lesson l WHERE l.description = :description"),
    @NamedQuery(name = "Lesson.findByIsActive", query = "SELECT l FROM Lesson l WHERE l.isActive = :isActive"),
    @NamedQuery(name = "Lesson.findByCreatedDate", query = "SELECT l FROM Lesson l WHERE l.createdDate = :createdDate"),
    @NamedQuery(name = "Lesson.findByUpdatedDate", query = "SELECT l FROM Lesson l WHERE l.updatedDate = :updatedDate")})
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "isActive")
    private Boolean isActive;
    @Basic(optional = false)
    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "updatedDate", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @OneToMany(mappedBy = "lessionId")
    @JsonIgnore
    private Set<Assignment> assignmentSet;
    @OneToMany(mappedBy = "lessonId")
    @JsonIgnore
    private Set<Document> documentSet;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
//    @JsonIgnore
    private Course courseId;

    @OneToMany(mappedBy = "lessonId")
    @JsonIgnore
    private Set<Video> videoSet;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }

}
