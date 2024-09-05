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
@Table(name = "user")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lastName")
    private String lastName;
//    @Size(max = 50)
    
    @Column(name = "password")
//    @Size(min = 0, max = 50, message = "Password must be between 0 and 50 characters")
    private String password;
    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "isActive")
    private Boolean isActive;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phoneNumber")
    private String phoneNumber;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Answerchoice> answerchoiceSet;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Certification> certificationSet;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Enrollment> enrollmentSet;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Score> scoreSet;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Teacher> teacherSet;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Essay> essaySet;
//    @OneToMany(mappedBy = "userId")
//    @JsonIgnore
//    private Set<Receipt> receiptSet;
    
    @Transient
    @JsonIgnore
    private MultipartFile file;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

//    @XmlTransient
//    public Set<Answerchoice> getAnswerchoiceSet() {
//        return answerchoiceSet;
//    }
//
//    public void setAnswerchoiceSet(Set<Answerchoice> answerchoiceSet) {
//        this.answerchoiceSet = answerchoiceSet;
//    }
//
//    @XmlTransient
//    public Set<Teacher> getTeacherSet() {
//        return teacherSet;
//    }
//
//    public void setTeacherSet(Set<Teacher> teacherSet) {
//        this.teacherSet = teacherSet;
//    }
//
//    @XmlTransient
//    public Set<Essay> getEssaySet() {
//        return essaySet;
//    }
//
//    public void setEssaySet(Set<Essay> essaySet) {
//        this.essaySet = essaySet;
//    }

}
