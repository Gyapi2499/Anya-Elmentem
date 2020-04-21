package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.Group;
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
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByEmail(user.getEmail());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
   @GetMapping(value = "/groups")
    public ResponseEntity<Object> getGroup() {        
        List<Group> party = userRepository.findByGroup();        
        return  ResponseEntity.ok(party);
    }
}
