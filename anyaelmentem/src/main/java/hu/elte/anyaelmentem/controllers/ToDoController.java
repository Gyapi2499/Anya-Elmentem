/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.ToDo;
import hu.elte.anyaelmentem.entities.User;
import hu.elte.anyaelmentem.entities.Group;
import hu.elte.anyaelmentem.entities.Chore;
import hu.elte.anyaelmentem.repositories.ChoreRepository;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.ToDoRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.time.temporal.ChronoUnit;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    ChoreRepository choreRepository;
    
    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody ToDo todo){
        Group group = groupRepository.findById(todo.getGroupId()).get();
        if(authenticatedUser.getUser().getRole()==User.Role.ADMIN 
           || group.getAdmins().contains(authenticatedUser.getUser())){
            if(todo.getFromDate()==null && todo.getToDate()==null){
                if(todo.getUserId()==null){
                    int min=168;
                    User minUser = new User();
                    authenticatedUser.getUser().getEmail();
                    LocalDateTime now = LocalDateTime.now();
                    DayOfWeek day = now.getDayOfWeek();
                    long dayNum = day.getLong(ChronoField.DAY_OF_WEEK);
                    LocalDateTime monday = now.withDayOfYear(now.getDayOfYear()-(int)dayNum-1).withHour(8).withMinute(0).withSecond(0);
                    for(User user: group.getUsers()){
                        int time = 0;
                        for(ToDo t:toDoRepository.findAllByUserId(user.getEmail()).get()){
                            if(t.getFromDate().isAfter(monday.withHour(0)) && t.getToDate().isBefore(monday.withHour(0).plusDays(7))){
                                time+=t.getFromDate().until(t.getToDate(), ChronoUnit.HOURS);
                            }
                            else if(t.getFromDate().isBefore(monday.withHour(0)) && t.getToDate().isAfter(monday.withHour(0))){
                                time+=monday.withHour(0).until(t.getToDate(), ChronoUnit.HOURS);
                            }
                            else if(t.getFromDate().isBefore(monday.withHour(0).plusDays(7)) && t.getToDate().isAfter(monday.withHour(0).plusDays(7))){
                                time+=t.getFromDate().until(monday.withHour(0).plusDays(7), ChronoUnit.HOURS);
                            }
                        }
                        if(time<min){
                            min = time;
                            minUser=user;
                        }
                    }
                    if(minUser.getEmail()==null){
                        return ResponseEntity.badRequest().build();
                    }
                    todo.setUserId(minUser.getEmail());
                    boolean right = false;
                    LocalDateTime startDay= monday;
                    while(!right && !monday.isAfter(startDay.withDayOfYear(startDay.getDayOfYear()+7).withHour(8))) {
                        right=true;
                        for(ToDo t:toDoRepository.findAllByUserId(minUser.getEmail()).get()){
                            if(monday.isAfter(t.getFromDate()) && monday.isBefore(t.getToDate())
                                    || monday.withHour(monday.getHour()+1).isAfter(t.getFromDate()) && monday.withHour(monday.getHour()+1).isBefore(t.getToDate())){
                                right=false;
                                break;
                            }
                        }
                    if(monday.getHour()!=22)
                        monday=monday.plusHours(1);
                    else
                        monday=monday.plusDays(1).withHour(0);
                    }
                    if(right){
                        todo.setFromDate(monday);
                        todo.setToDate(monday.plusHours(1));
                        if(!choreRepository.findById(todo.getChores()).isPresent()){
                            choreRepository.save(new Chore(todo.getChores()));
                        }
                        toDoRepository.save(todo);
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.badRequest().build();
                }
                    LocalDateTime now = LocalDateTime.now();
                    DayOfWeek day = now.getDayOfWeek();
                    long dayNum = day.getLong(ChronoField.DAY_OF_WEEK);
                    LocalDateTime monday = now.withDayOfYear(now.getDayOfYear()-(int)dayNum-1).withHour(8).withMinute(0).withSecond(0);
                    boolean right = true;
                    List<ToDo> todos = toDoRepository.findAllByUserId(todo.getUserId()).get();
                    LocalDateTime startDay= monday;
                    while(right && !monday.isAfter(startDay.withDayOfYear(startDay.getDayOfYear()+7).withHour(8))){
                        for(ToDo t:todos){
                            if(monday.isAfter(t.getFromDate()) && monday.isBefore(t.getToDate())
                                    || monday.withHour(monday.getHour()+1).isAfter(t.getFromDate()) && monday.withHour(monday.getHour()+1).isBefore(t.getToDate())){
                                right=false;
                                break;
                            }
                        }
                    System.out.println(monday);
                    if(monday.getHour()!=22)
                        monday=monday.plusHours(1);
                    else
                        monday=monday.plusDays(1).withHour(0);
                    }
                    if(right){
                        todo.setFromDate(monday);
                        todo.setToDate(monday.plusHours(1));
                        if(!choreRepository.findById(todo.getChores()).isPresent()){
                            choreRepository.save(new Chore(todo.getChores()));
                        }
                        toDoRepository.save(todo);
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.badRequest().build();
            }
            if(!choreRepository.findById(todo.getChores()).isPresent()){
                choreRepository.save(new Chore(todo.getChores()));
            }
            toDoRepository.save(todo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
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
    

    
    @PostMapping("/readyOrNot/{id}")
    public ResponseEntity<ToDo> readyOrNot(@PathVariable int id){
        ToDo ron = toDoRepository.findById(id).get();
        ron.setReady(true);
        return ResponseEntity.ok(toDoRepository.save(ron));
    }


   /**
    * Todo törlés metódus
    */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        toDoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/toDos/{email}")
    public ResponseEntity<List<ToDo>> getAll(@PathVariable String email){
        return ResponseEntity.ok(toDoRepository.findAllByUserId(email).get());
    }

}
