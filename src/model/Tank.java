/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public abstract class Tank {
    protected int x;//coordinates of tank
    protected int y;
    protected int tankWidth;
    protected int armor;
    protected ArrayList<Missle> unitsOfFire;
    protected ArrayList<Tank> listOfEnemies;//beware! you are in enemies list
    protected int life;
    protected int tubeDirection;
    protected int shutPower;

    public Tank(int armor, ArrayList<Missle> unitsOfFire, 
            ArrayList<Tank> listOfEnemies, int life, int tubeDirection, 
            int x, int y, int tankWidth) {
        this.armor = armor;
        this.unitsOfFire = unitsOfFire;
        this.listOfEnemies = listOfEnemies;
        this.life = life;
        this.tubeDirection = tubeDirection;
        this.x = x;                         //is
        this.y = y;                         //it
        this.tankWidth = tankWidth; // = 3  //needed?
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean isAlive(){
        return (this.life > 0);
    }
    
//    public void getListOfEmenied(BattleField instance){
//        this.listOfEnemies = instance.listOfTanks;
//    }
    
    public String getHit(int hit){ //must be overriden in TankPC(Tank offender)
        int damage = this.armor - hit;
        if (damage <= 0){
            this.life -= damage;
            this.armor = 0;
        }else{
            this.armor -= damage;
        }
        if (this.life <= 0) this.explode();
        return "Say ouch";
    }
    
    public String gotEnemy(){//called by missle
        return "Say take it";//and display points
    }
    
    public void explode(){
        
    }
    
    public abstract void shotEnemy(Tank target);
    public abstract String selectEnemy();//operates with 3 next methods and threats enemy(PC)
    public abstract int setTubeDirections(int step);
    public abstract int setShotPower(int step);
    public abstract Missle setMissle();
    
}
