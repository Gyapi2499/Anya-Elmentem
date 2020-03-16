/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.ToDo;
import hu.elte.anyaelmentem.entities.User;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.ToDoRepository;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Máté
 */
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
    
    @GetMapping("/myToDo")
    public ResponseEntity<List<ToDo>> getMyToDos(){
        return ResponseEntity.ok(toDoRepository.findAllByUser(authenticatedUser.getUser()).get());
    }
    
    @GetMapping("/groupToDo/{id}")
    public ResponseEntity <List<List<ToDo>>> getGroupToDos(@PathVariable int id){
        List<User> users = groupRepository.findById(id).get().getUsers();        
        List<List<ToDo>> GTDList = new ArrayList<List<ToDo>>();
        for(User user : users){
           GTDList.add(user.getToDos());
        }
        return ResponseEntity.ok(GTDList);
    }
    
    
    @PostMapping("/didIt")
    public ResponseEntity<List<List<ToDo>>> didIt(int id,List<ToDo> tdList){
        if(authenticatedUser.getUser().getRole()== User.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
            List<List<ToDo>> listToDo=new ArrayList();
            for(ToDo t : tdList){
                t.getUser().getToDos().add(t);
                userRepository.save(t.getUser());
            }
            toDoRepository.saveAll(tdList);           
            for(User user : groupRepository.findById(id).get().getUsers()){
                listToDo.add(user.getToDos());
            }
            return ResponseEntity.ok(listToDo);
        }
        return ResponseEntity.badRequest().build();         
    }
            
    
    @PostMapping("/myDay")
    public ResponseEntity<List<ToDo>> myDay(@RequestBody List<ToDo> todos){
        for(ToDo td : todos){
           authenticatedUser.getUser().getToDos().add(td);           
        }
        toDoRepository.saveAll(todos);
        userRepository.save(authenticatedUser.getUser());
        return ResponseEntity.ok(authenticatedUser.getUser().getToDos());
        
    }
}
