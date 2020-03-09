/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.entities;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class ToDos{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private int id;
            
    @JoinColumn
    private String user;
    
    
    @JoinColumn
    private String chores;
     
    @Column(nullable=false)
    private LocalDateTime fromDate;
            
    @Column(nullable=false)
    private LocalDateTime toDate;
   
    @Column(nullable = false)
    private int groupId;
                   
}
