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
    

}
