/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.Change;
import hu.elte.anyaelmentem.entities.ToDo;
import hu.elte.anyaelmentem.repositories.ChangeRepository;
import hu.elte.anyaelmentem.repositories.ToDoRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
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
 * @author palii
 */
@CrossOrigin
@RestController
@RequestMapping("/change")
public class ChangeController {
    
    @Autowired
    private ChangeRepository changeRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @Autowired
    private ToDoRepository toDoRepository;
    
    @PostMapping("/add")
    public ResponseEntity change(@RequestBody Change change){
        changeRepository.save(change);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/accept/{id}")
    public ResponseEntity accept(@PathVariable int id){
        Change change = changeRepository.findById(id).get();
        if(this.authenticatedUser.getUser().getEmail().equals(change.getUser2().getEmail())){
            List<ToDo> toDo1 = toDoRepository.findAllByUserId(authenticatedUser.getUser().getEmail()).get();
            List<ToDo> toDo2 = toDoRepository.findAllByUserId(change.getUser2().getEmail()).get();
            for(ToDo t : toDo1){
                if(change.getTodo1().getId() == t.getId()){
                    t.setUserId(change.getUser2().getEmail());
                    toDoRepository.save(t);
                }
            }
            for(ToDo t : toDo2){
                if(change.getTodo2().getId() == t.getId()){
                    t.setUserId(change.getUser1().getEmail());
                    toDoRepository.save(t);
                }
            }
            changeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    
    @PostMapping("/decline/{id}")
    public ResponseEntity decline(@PathVariable int id){
        Change change = changeRepository.findById(id).get();
        if(this.authenticatedUser.getUser().getEmail().equals(change.getUser2().getEmail())){
            changeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getAll")
    public ResponseEntity get(){
        return ResponseEntity.ok(changeRepository.findAllByUser2Email(authenticatedUser.getUser().getEmail()));
    }
}
