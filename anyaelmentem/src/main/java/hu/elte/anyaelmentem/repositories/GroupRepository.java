/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;

import hu.elte.anyaelmentem.entities.Group;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author stefa
 */
public interface GroupRepository extends CrudRepository<Group, Integer> {
    Optional <Group> findById(int id);
    List<Group> findAll();
    
    @Query("Select g from Group g,User u where u MEMBER g.admins and u.email=:email")
    List <Group> findAdminG(@Param(value="email") String email);

    @Query("Select g from Group g,User u where u MEMBER g.users and u.email=:email")
    List <Group> findUserG(@Param(value="email") String email);
       
}
