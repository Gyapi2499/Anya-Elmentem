/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.anyaelmentem.controllers.ChoreController;
import hu.elte.anyaelmentem.controllers.UserController;
import hu.elte.anyaelmentem.entities.User;
import hu.elte.anyaelmentem.repositories.ChoreRepository;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.ToDoRepository;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.util.Base64;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author palii
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest()
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
   
    @MockBean
    ChoreRepository choreRepository;
    
    @MockBean
    AuthenticatedUser AuthenticatedUser;
    
    @MockBean
    GroupRepository groupRepository;
    
    @MockBean
    UserRepository userRepository;
    
    @MockBean
    ToDoRepository toDoRepository;
    
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    
    
    @BeforeClass
    void init(){
        
    }
    
    @BeforeEach
    void auth() throws Exception{
       String token ="anya@anya.com:asdasd";
       String encoded = new String(Base64.getEncoder().encode(token.getBytes()));
        System.out.println(encoded);
        mockMvc.perform(post("/users/login",new User()).header("Access-Control-Allow-Origin", "*").header("Authorization" , "Basic "+encoded).contentType("application/json")).andExpect(status().is(200));
    }
    
    @Test
    void whenValidInput_thenReturns200() throws Exception {
        mockMvc.perform(get("/chores/allChores").contentType("application/json")).andExpect(status().is(200));
  }
}
