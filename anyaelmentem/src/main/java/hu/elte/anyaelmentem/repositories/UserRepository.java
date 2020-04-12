/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;

import hu.elte.anyaelmentem.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Máté
 */
public interface UserRepository extends CrudRepository<User, String>{
    Optional<User> findByEmail(String email);
    List<User> findAll();    
}

