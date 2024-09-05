/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.Table;
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
@Table(name = "question")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id"),
    @NamedQuery(name = "Question.findByName", query = "SELECT q FROM Question q WHERE q.name = :name"),
})
public class Question implements Serializable {

//    @OneToMany(mappedBy = "questionId")
//    private Set<Answerchoice> answerchoiceSet;
//    @OneToMany(mappedBy = "questionId")
//    private Set<Essay> essaySet;
//    @OneToMany(mappedBy = "questionId")
//    private Set<Choice> choiceSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @ManyToOne
    private Tag tagId;
    
    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    @ManyToOne
    private Assignment assignmentId;
    
//    @OneToMany(mappedBy = "questionId")
//    @JsonIgnore
//    private Set<Answerchoice> answerchoiceSet;
//    
//    @OneToMany(mappedBy = "questionId")
//    @JsonIgnore
//    private Set<Essay> essaySet;
    
//    @OneToMany(mappedBy = "questionId")
//    @JsonIgnore
//    private Set<Choice> choiceSet;

    /**
     * @return the assignmentId
     */
    public Assignment getAssignmentId() {
        return assignmentId;
    }

    /**
     * @param assignmentId the assignmentId to set
     */
    public void setAssignmentId(Assignment assignmentId) {
        this.assignmentId = assignmentId;
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
//    public Set<Essay> getEssaySet() {
//        return essaySet;
//    }
//
//    public void setEssaySet(Set<Essay> essaySet) {
//        this.essaySet = essaySet;
//    }
//
//    @XmlTransient
//    public Set<Choice> getChoiceSet() {
//        return choiceSet;
//    }
//
//    public void setChoiceSet(Set<Choice> choiceSet) {
//        this.choiceSet = choiceSet;
//    }

}
