/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.entities;

import static java.lang.ProcessBuilder.Redirect.from;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author stefa
 */
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
