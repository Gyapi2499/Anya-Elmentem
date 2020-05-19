/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.repositories;

import hu.elte.anyaelmentem.entities.Change;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author palii
 */
public interface ChangeRepository extends CrudRepository<Change, Integer> {
    List<Change> findAllByUser2Email(String id);
}
