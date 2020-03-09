/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;

import hu.elte.anyaelmentem.entities.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Máté
 */
public interface UserRepository extends CrudRepository<Users, String>{
    Optional<Users> findByEmail(String email);
    List<Users> findAll();
}

