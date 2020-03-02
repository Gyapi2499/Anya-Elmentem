/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.entities;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author stefa
 */
public class Chores {
    @Id
    @Column(nullable = false)
    private String name;
}
