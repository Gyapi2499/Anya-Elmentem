/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author stefa
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Integer id;
            
    private String userId;
    
    @JoinColumn
    private String chores;
     
    @Column(nullable=false)
    private LocalDateTime fromDate;
            
    @Column(nullable=false)
    private LocalDateTime toDate;
    
    @Column(nullable = false)
    private int groupId;
    
    @Column(nullable=false)
    private Boolean ready;
                   
}
