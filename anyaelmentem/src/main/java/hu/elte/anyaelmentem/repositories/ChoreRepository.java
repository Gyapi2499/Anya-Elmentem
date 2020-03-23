/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;


import org.springframework.data.repository.CrudRepository;
import hu.elte.anyaelmentem.entities.Chore;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


public interface ChoreRepository extends CrudRepository<Chore, String> {
    Optional <Chore> findById(String id);
    
    @Query(value="SELECT*FROM CHORE", nativeQuery=true)
    List <Chore> findAll();

}
