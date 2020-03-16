/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.Group;
import hu.elte.anyaelmentem.entities.User;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
   public ResponseEntity<Group> newGroup(List<User> myMember){
       Group nGroup= new Group();
       nGroup.setUsers(myMember);
       nGroup.getAdmins().add(authenticatedUser.getUser());
       return ResponseEntity.ok(groupRepository.save(nGroup));       
   }
   
   @PostMapping("/addMember")//csapathoz adok hozzá
   public ResponseEntity<Group> addMember(int id, User addM){
       if(authenticatedUser.getUser().getRole()== User.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
       Group temp=groupRepository.findById(id).get();
       temp.getUsers().add(addM);
       return ResponseEntity.ok(groupRepository.save(temp));       
   } return ResponseEntity.badRequest().build(); 
}
   
   @PostMapping("/shareAdmin")
   public ResponseEntity<Group> shareAdmin(int id, List<User> user){
       if(authenticatedUser.getUser().getRole()== User.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
       Group temp=groupRepository.findById(id).get();
       for(User u:user){
       temp.getUsers().remove(u);
       temp.getAdmins().add(u);
       }
       return ResponseEntity.ok(groupRepository.save(temp));
   } return ResponseEntity.badRequest().build(); 
}
   
    @PostMapping("/takeAdmin")
    public ResponseEntity<Group> takeAdmin(int id, List<User> user){
       if(authenticatedUser.getUser().getRole()== User.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
       Group temp=groupRepository.findById(id).get();
       for(User u:user){
       temp.getUsers().add(u);
       temp.getAdmins().remove(u);
       }
       return ResponseEntity.ok(groupRepository.save(temp));
   } return ResponseEntity.badRequest().build(); 
}
    
    @DeleteMapping("/deleteGroup/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id){
        if(authenticatedUser.getUser().getRole()== User.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
        groupRepository.delete(groupRepository.findById(id).get());
        return ResponseEntity.ok().build();
       } return ResponseEntity.badRequest().build(); 
    }
    
    @PostMapping("/deleteMember")
    public ResponseEntity<Group> deleteMember(int id, User deleteM){
       if(authenticatedUser.getUser().getRole()== User.Role.ADMIN || groupRepository.findById(id).get().getAdmins().contains(authenticatedUser.getUser())) {
       Group temp=groupRepository.findById(id).get();
       temp.getUsers().remove(deleteM);
       return ResponseEntity.ok(groupRepository.save(temp));     
       } return ResponseEntity.badRequest().build(); 
    }
}