/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.Group;
import hu.elte.anyaelmentem.entities.Users;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private UserRepository userRepository;
    
   
    /**    
    * uj csapat 
    */
   @PostMapping("/newGroup")
   public ResponseEntity<Group> newGroup(List<Users> myMember){
       Group nGroup= new Group();
       nGroup.setUsers(myMember);
       nGroup.getAdmins().add(authenticatedUser.getUser());
       return ResponseEntity.ok(groupRepository.save(nGroup));       
   }
   
   @PostMapping("/addMember")//csapathoz adok hozzá
   public ResponseEntity<Group> addMember(int id, Users addM){
       Group temp=groupRepository.findById(id).get();
       temp.getUsers().add(addM);
       return ResponseEntity.ok(groupRepository.save(temp));       
   }

}