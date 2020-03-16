package hu.elte.anyaelmentem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Column(nullable = false)
    private String name;

    @Id
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JsonIgnore
    private List<Group> groups;
    
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<ToDos> toDos;
    
    public enum Role{
        ADMIN,USER
    }
}
