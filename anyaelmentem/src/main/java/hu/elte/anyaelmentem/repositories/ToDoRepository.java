/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;

import hu.elte.anyaelmentem.entities.ToDo;
import hu.elte.anyaelmentem.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Máté
 */
public interface ToDoRepository extends CrudRepository<ToDo, Integer>{//todos-olyan obektumokat fog visszadnoi a repo és Integer -olyan tipusú az id
    Optional<List<ToDo>> findAllByUser(User user);//spring black magic
    List<ToDo> findAll();
}
