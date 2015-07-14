/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author user
 */
public abstract class Tank {
    protected int x;//coordinates of tank
    protected int y;
    //protected int tankWidth;
    protected String name;
    protected int armor;
    protected HashMap<String,Integer> unitsOfFire;
    protected ArrayList<Tank> listOfEnemies;//beware! you are in enemies list
    protected int life;
    protected int tubeDirection;
    protected int shutPower;
    
    protected LinkedList<String> warlords = new LinkedList<String>(){{//Anonymous class using
        add("Alexander the Great");add("Leonidas I"); add("Genghis Khan"); 
        add("Hannibal Barca"); add("Gaius Julius Caesar");add("Napoleon Bonaparte");
        add("Spartacus");add("Salah-ad-Din");add("Stepan Bandera");add("Atilla");
        add("Hernan Kortes");add("Oda Nobunaga");add("Eugen Konovalets");add("Nestor Makhno");
    }};

    public Tank(String name,int armor, /*HashMap<String,Integer> unitsOfFire, 
            ArrayList<Tank> listOfEnemies,*/ int life, /*int tubeDirection,*/ 
            int x, int y) {
        Random rand = new Random();
        this.name = (name != null)?name:warlords.remove(rand.nextInt(warlords.size()));//2 equal names may occur
        this.armor = armor;
        //this.unitsOfFire = unitsOfFire;
        //this.listOfEnemies = listOfEnemies;
        this.life = life;
        //this.tubeDirection = tubeDirection;
        this.x = x;                         //is
        this.y = y;                         //it
        //this.tankWidth = tankWidth; // = 3  //needed?
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean isAlive(){
        return this.life > 0;
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
