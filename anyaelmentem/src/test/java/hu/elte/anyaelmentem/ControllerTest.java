/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.anyaelmentem.entities.Chore;
import hu.elte.anyaelmentem.entities.ToDo;
import hu.elte.anyaelmentem.entities.Group;
import hu.elte.anyaelmentem.entities.RegisterDTO;
import hu.elte.anyaelmentem.entities.User;
import hu.elte.anyaelmentem.entities.User.Role;
import hu.elte.anyaelmentem.repositories.ChangeRepository;
import hu.elte.anyaelmentem.repositories.ChoreRepository;
import hu.elte.anyaelmentem.repositories.GroupRepository;
import hu.elte.anyaelmentem.repositories.ToDoRepository;
import hu.elte.anyaelmentem.repositories.UserRepository;
import hu.elte.anyaelmentem.security.AuthenticatedUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Java6Assertions.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author palii
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest()
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
   
    @MockBean
    ChoreRepository choreRepository;
    
    @MockBean
    AuthenticatedUser authenticatedUser;
    
    @MockBean
    GroupRepository groupRepository;
    
    @MockBean
    ChangeRepository changeRepository;
    
    @MockBean
    UserRepository userRepository;
    
    @MockBean
    ToDoRepository toDoRepository;
    
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    
    String token ="anya@anya.com:asdasd";
    String encoded = new String(Base64.getEncoder().encode(token.getBytes()));
    
    @Value("${need.test}")
    private boolean test;

    
    @Before
    void init(){
    }
    
    @BeforeEach
    void auth() throws Exception{
        //mockMvc.perform(post("/users/login").header("Access-Control-Allow-Origin", "*").header("Authorization" , "Basic "+encoded).contentType("application/json"));
    }
    @WithMockUser("user")
    @Test
    void whenValidInput_thenReturns200() throws Exception {
        if(test){
            mockMvc.perform(get("/chores/allChores").contentType("application/json")).andExpect(status().is(200));
        }else{
            mockMvc.perform(get("/chores/allChores").header("Access-Control-Allow-Origin", "*").header("Authorization" , "Basic "+encoded).contentType("application/json")).andExpect(status().is(401));
        }
    }
    @WithMockUser("user")
    @Test
    void usersTest() throws Exception {
        if(test){
            mockMvc.perform(get("/users/users").contentType("application/json")).andExpect(status().is(200));
        }else{
            mockMvc.perform(get("/users/users").header("Access-Control-Allow-Origin", "*").header("Authorization" , "Basic "+encoded).contentType("application/json")).andExpect(status().is(401));
        }
    }
     
    
    @WithMockUser("user")
    @Test
    void whenValidInput_thenReturnsUserResource() throws Exception {
        Chore choress = new Chore("asd");
        when(choreRepository.save(any(Chore.class))).thenReturn(choress);
        
        
        MvcResult mvcResult = mockMvc.perform(post("/chores/newChore/asd").contentType("application/json")).andReturn();
        System.out.println( mvcResult.getResponse().getContentAsString());
        Chore chore = new Chore("asd");
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(chore)).isEqualToIgnoringWhitespace(actualResponseBody);
    }
    @WithMockUser("user")
    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {
      Chore chore = new Chore("asd");
      mockMvc.perform(post("/chores/newChore/asd").contentType("application/json"));

      ArgumentCaptor<Chore> choreCaptor = ArgumentCaptor.forClass(Chore.class);
      verify(choreRepository, times(1)).save(choreCaptor.capture());
      assertThat(choreCaptor.getValue().getName()).isEqualTo(chore.getName());
    }
    /*@WithMockUser("user")
    @Test
    void newGroupTest() throws Exception{
        User u1= new User("bori","bori@bori.hu","asd",User.Role.ADMIN,null);
        //authenticatedUser.setUser(u1);
        User u2= new User("bori2","bori2@bori.hu","asd",User.Role.ADMIN,null);
        List<User> ulist= new ArrayList<>();
        ulist.add(u1);
        ulist.add(u2);
        
        Group group = new Group(1,new ArrayList<User>(),ulist);
        
        List<Group> glist = new ArrayList();
        glist.add(group);
          u1.setGroups(glist);
          u2.setGroups(glist);
        
        mockMvc.perform(post("/group/newGroup").content(objectMapper.writeValueAsString(ulist)).contentType("application/json"));
        ArgumentCaptor<Group> groupCaptor = ArgumentCaptor.forClass(Group.class);
        verify(groupRepository, times(1)).save(groupCaptor.capture());
        assertThat(groupCaptor.getValue().getUsers().get(0).getName()).isEqualTo(ulist.get(0).getName());
        
        
        
    }
    @WithMockUser("user")
    @ Test
    void registerTest() throws Exception{
        RegisterDTO u1= new RegisterDTO("Bori","bori@bori.hu","pingvin"); 
        //mockMvc.perform(post("/users/users/register",u1).contentType("application/json"));
        mockMvc.perform(post("/users/users/register").content(objectMapper.writeValueAsString(u1)).contentType("application/json"));
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
       
         assertThat(userCaptor.getValue().getName()).isEqualTo("Bori");
         assertThat(userCaptor.getValue().getEmail()).isEqualTo("bori@bori.hu");
         assertThat(userCaptor.getValue().getRole()).isEqualTo(Role.USER);
    }


    /*@Test
    void AddToDoTest() throws Exception {
    ToDo todo = new ToDo(null, "asd@asd.hu", "mosogatas", LocalDateTime.now(), LocalDateTime.now(), 1, false);
    mockMvc.perform(post("/todo/add", todo).contentType("application/json"));
    
    ArgumentCaptor<ToDo> todoCaptor = ArgumentCaptor.forClass(ToDo.class);
    verify(toDoRepository, times(1)).save(todoCaptor.capture());
    assertThat(todoCaptor.getValue().getUserId()).isEqualTo(todo.getUserId());
    assertThat(todoCaptor.getValue().getChores()).isEqualTo(todo.getChores());
    assertThat(todoCaptor.getValue().getFromDate()).isEqualTo(todo.getFromDate());
    assertThat(todoCaptor.getValue().getToDate()).isEqualTo(todo.getToDate());
    assertThat(todoCaptor.getValue().getGroupId()).isEqualTo(todo.getGroupId());
    assertThat(todoCaptor.getValue().getReady()).isEqualTo(todo.getReady());

    
    }*/
    @WithMockUser("user")
    @Test
    void readyOrNotTest() throws Exception {
        ToDo todos = new ToDo(1, "asd@asd.hu", "mosogatas", LocalDateTime.now(), LocalDateTime.now(), 1, false);
        when(toDoRepository.findById(1)).thenReturn(Optional.of(todos));     
        ToDo todo = new ToDo(1, "asd@asd.hu", "mosogatas", LocalDateTime.now(), LocalDateTime.now(), 1, false);
               
        mockMvc.perform(post("/todo/readyOrNot/1").content(objectMapper.writeValueAsString(todo)).contentType("application/json"));
         ArgumentCaptor<ToDo> todoCaptor = ArgumentCaptor.forClass(ToDo.class);
         verify(toDoRepository, times(1)).save(todoCaptor.capture());
         assertThat(todoCaptor.getValue().getReady()).isEqualTo(true);       
    }
    @WithMockUser("user")
    @Test
    void getMemberListTest() throws Exception {
        User u1= new User("bori","bori@bori.hu","pingvin",User.Role.ADMIN,null);        
        User u2= new User("andris","andris@bori.hu","jegesmaci",User.Role.ADMIN,null);
        List<User> ulist= new ArrayList<>();
        ulist.add(u1);
        ulist.add(u2);       
        Group group = new Group(1,new ArrayList<User>(),ulist); 
        List<Group> glist = new ArrayList();
        glist.add(group);//
        u1.setGroups(glist);
        u2.setGroups(glist);
        when(groupRepository.findById(1)).thenReturn(Optional.of(group)); 
        Group g = new Group(1,new ArrayList<User>(),ulist);
       
            mockMvc.perform(get("/group/getMemberList/1").contentType("application/json")).andExpect(status().is(200));
    }   
    @WithMockUser("user")
    @Test
    void getWeekTest() throws Exception {
            ToDo todos = new ToDo(1, "asd@asd.hu", "mosogatas", LocalDateTime.now(), LocalDateTime.now(), 1, false);
            List<ToDo> ltd= new ArrayList();
            ltd.add(todos);
            when(toDoRepository.findAllByUserId("asd@asd.hu")).thenReturn(Optional.of(ltd));
            ToDo todo = new ToDo(1, "asd@asd.hu", "mosogatas", LocalDateTime.now(), LocalDateTime.now(), 1, false);

            mockMvc.perform(get("/todo/getWeek/asd@asd.hu").contentType("application/json")).andExpect(status().is(200));   
    }
      
}

