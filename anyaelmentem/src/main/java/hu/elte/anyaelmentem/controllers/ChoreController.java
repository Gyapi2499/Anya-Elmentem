/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.controllers;

import hu.elte.anyaelmentem.entities.Chore;
import hu.elte.anyaelmentem.repositories.ChoreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Máté
 */
@CrossOrigin
@RestController
@RequestMapping("/chores")
public class ChoreController {

    @Autowired
    private ChoreRepository choreRepository;
    
    /**
     * Összes feladat lekérdezése
     */
    @GetMapping("/allChores")
    public ResponseEntity<List <Chore>> allChores(){
        return ResponseEntity.ok(choreRepository.findAll());
    }
    
    /**
     * Feladat törlése
     */
    @DeleteMapping("/deleteChore/{id}")
    public ResponseEntity<Void> deleteChore(@PathVariable String id){
        choreRepository.delete(choreRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }
    
    /**
     * Feladat létrehozása
     */
    @PostMapping("/newChore/{id}")
    public ResponseEntity<Chore> newChore(@PathVariable String id){
        Chore chore = new Chore(id);
        return ResponseEntity.ok(choreRepository.save(chore));
    }
    
}