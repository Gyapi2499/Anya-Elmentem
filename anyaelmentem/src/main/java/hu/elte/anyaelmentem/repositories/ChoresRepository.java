/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;


import org.springframework.data.repository.CrudRepository;
import hu.elte.anyaelmentem.entities.Chores;
import java.util.Optional;


public interface ChoresRepository extends CrudRepository<Chores, String> {
    Optional <Chores> findById(String id);

}
