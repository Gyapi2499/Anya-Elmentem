package hu.elte.anyaelmentem.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
    private String password;

    @ManyToMany
    private List<Group> groups;
}
