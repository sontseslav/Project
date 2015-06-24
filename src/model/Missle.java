/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public abstract class Missle{
        protected int weight;
        protected int imposedArea; //radius of explosion
        protected int yield; //points of hit in explosion area

        public Missle() {
        }
        
        public abstract void explode();
        
        public abstract int[][]fly();
        
    }
