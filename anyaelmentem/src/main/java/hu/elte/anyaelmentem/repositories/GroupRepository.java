/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;

import hu.elte.anyaelmentem.entities.Group;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author stefa
 */
public interface GroupRepository extends CrudRepository<Group, Integer> {
    Optional <Group> findById(int id);
       
}
