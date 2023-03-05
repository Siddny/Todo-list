
package com.sunflashtech.interview.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Data
@EqualsAndHashCode(callSuper = false)
public class Task extends Auditable<String>{

  public enum PriorityLevel {
    HIGH,MEDIUM,LOW;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = false, nullable = false)
  private String name;
    
  @Size(max = 255, message = "Maximum description length: 255 characters")
  @Column(unique = false, nullable = true)
  private String description;

  @Column(nullable = true, columnDefinition = "boolean default false")
  private Boolean completed;

  @Enumerated(EnumType.STRING)
  @Column(unique = false, nullable = true)
  private PriorityLevel priorityLevel;
  
  

}
