/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.ToDos;
import hu.elte.anyaelmentem.entities.Users;
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
    public ResponseEntity<List<ToDos>> getMyToDos(){
        return ResponseEntity.ok(toDoRepository.findAllByUser(authenticatedUser.getUser()).get());
    }
    
    @GetMapping("/groupToDo/{id}")
    public ResponseEntity <List<List<ToDos>>> getGroupToDos(@PathVariable int id){
        List<Users> users = groupRepository.findById(id).get().getUsers();        
        List<List<ToDos>> GTDList = new ArrayList<List<ToDos>>();
        for(Users user : users){
           GTDList.add(user.getToDos());
        }
        return ResponseEntity.ok(GTDList);
    }
    
    
    @PostMapping("/didIt")
    public ResponseEntity<List<List<ToDos>>> didIt(int id,List<ToDos> tdList){
        if(authenticatedUser.getUser().getRole()== Users.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
            List<List<ToDos>> listToDo=new ArrayList();
            for(ToDos t : tdList){
                t.getUser().getToDos().add(t);
                userRepository.save(t.getUser());
            }
            toDoRepository.saveAll(tdList);           
            for(Users user : groupRepository.findById(id).get().getUsers()){
                listToDo.add(user.getToDos());
            }
            return ResponseEntity.ok(listToDo);
        }
        return ResponseEntity.badRequest().build();         
    }
            
    
    @PostMapping("/myDay")
    public ResponseEntity<List<ToDos>> myDay(@RequestBody List<ToDos> todos){
        for(ToDos td : todos){
           authenticatedUser.getUser().getToDos().add(td);           
        }
        toDoRepository.saveAll(todos);
        userRepository.save(authenticatedUser.getUser());
        return ResponseEntity.ok(authenticatedUser.getUser().getToDos());
        
    }
}
