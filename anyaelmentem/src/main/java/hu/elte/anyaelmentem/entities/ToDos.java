/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author stefa
 */
@Entity
public class ToDos {
    @Id
            
    @Column(nullable = false)
    private Users user;
    
    @Column(nullable = false)
    private Chores chores;
     
    @Column(nullable = false)
    private Date from;
            
    @Column (nullable = false)
    private Date to;
   
    @Column(nullable = false)
    private int group_id;
                   
}
