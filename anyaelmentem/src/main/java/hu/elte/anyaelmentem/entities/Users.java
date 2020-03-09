package hu.elte.anyaelmentem.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;

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
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Group> groups;
}
