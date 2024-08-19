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
import javax.persistence.Transient;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "course")
@XmlRootElement
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"),
    @NamedQuery(name = "Course.findByDescription", query = "SELECT c FROM Course c WHERE c.description = :description"),
    @NamedQuery(name = "Course.findByIsActive", query = "SELECT c FROM Course c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Course.findByCreatedDate", query = "SELECT c FROM Course c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Course.findByUpdatedDate", query = "SELECT c FROM Course c WHERE c.updatedDate = :updatedDate"),
    @NamedQuery(name = "Course.findByPrice", query = "SELECT c FROM Course c WHERE c.price = :price"),
    @NamedQuery(name = "Course.findByDiscount", query = "SELECT c FROM Course c WHERE c.discount = :discount"),
    @NamedQuery(name = "Course.findByImage", query = "SELECT c FROM Course c WHERE c.image = :image")})
public class Course implements Serializable {

    @OneToMany(mappedBy = "courseId")
    private Set<ReceiptDetail> recepitDetailSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "{course.name.errMsg}")
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "isActive")
    private Boolean isActive;
    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private float discount;
    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "courseId")
    @JsonIgnore
    private Set<Lesson> lessonSet;

    @OneToMany(mappedBy = "courseId")
    @JsonIgnore
    private Set<Video> videoSet;

    @OneToMany(mappedBy = "courseId")
    @JsonIgnore
    private Set<Certification> certificationSet;

    @OneToMany(mappedBy = "courseId")
    @JsonIgnore
    private Set<Enrollment> enrollmentSet;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Category categoryId;
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Tag tagId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Teacher teacherId;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }
}
