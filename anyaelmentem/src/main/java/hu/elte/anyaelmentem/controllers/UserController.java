package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.Group;
import hu.elte.anyaelmentem.entities.RegisterDTO;
import hu.elte.anyaelmentem.entities.User;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
     @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO inUser) {
        Optional<User> oUser = userRepository.findByEmail(inUser.getEmail());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userRepository.save(new User(inUser.getUsername(),inUser.getEmail(),passwordEncoder.encode(inUser.getPassword()),User.Role.USER,null)));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
   @GetMapping(value = "/groups/{id}")
    public ResponseEntity<Object> getGroup(@PathVariable String id) {   
        User user = userRepository.findByEmail(id).get();
        List<Integer> number= new ArrayList<>();
        List<Group> party = userRepository.findByGroup(); 
        for(int i=0;i<party.size();i++){
            List<List<User>> u = new ArrayList<>();
            System.out.println(party.get(i).getId());
            u.add(party.get(i).getUsers());
            
            for(int j=0;j<u.size();j++){
                System.out.println("kiscica");
                if(u.get(j).contains(user)){
                    
                    number.add(party.get(i).getId());
                }
            }
            
        }
        return  ResponseEntity.ok(number);
    }
}
