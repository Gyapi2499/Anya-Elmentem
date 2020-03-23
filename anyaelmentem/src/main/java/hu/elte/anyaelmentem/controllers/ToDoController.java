/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.ToDo;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.ToDoRepository;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/todo")
public class ToDoController {
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @Autowired
    private ToDoRepository toDoRepository;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private UserRepository userRepository;    
    
    @GetMapping("/getWeek/{userId}")
    public ResponseEntity<List<ToDo>> getWeek(@PathVariable String userId){
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek day = now.getDayOfWeek();
        long dayNum = day.getLong(ChronoField.DAY_OF_WEEK);
        LocalDateTime monday = now.withDayOfYear(now.getDayOfYear()-(int)dayNum-1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime nextMonday = now.withDayOfYear(now.getDayOfYear()-(int)dayNum+7).withHour(0).withMinute(0).withSecond(0);
        
        List<ToDo> weekToDo= new ArrayList<ToDo>();
                
        for(ToDo t : toDoRepository.findAllByUserId(userId).get()){
            if(t.getToDate().compareTo(monday)>0 && t.getToDate().compareTo(nextMonday)<0 ){
                weekToDo.add(t);                
            }
        } 
        return ResponseEntity.ok(weekToDo);       
    }
    
   /**
    * Todo törlés metódus
    */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        toDoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
