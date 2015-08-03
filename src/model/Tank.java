/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement(name = "Tank")
public abstract class Tank implements Observable{
    protected int x;//coordinates of tank
    protected int y;
    public ArrayList<Shape> tankShape;
    protected Scene scene;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Armor")
    public int armor;
    protected HashMap<String,Integer> unitsOfFire;
    protected ArrayList<Tank> listOfEnemies;//beware! you are in enemies list
    @XmlElement(name = "Health")
    public int life;
    protected ArrayList<double[]> missilePath;
    protected double explodeR;
    protected ArrayList<Observer> observers;
    protected BattleField instance;

    public Tank(){}

    public Tank(String name,int armor, /*HashMap<String,Integer> unitsOfFire, 
            ArrayList<Tank> listOfEnemies,*/ int life, /*int tubeDirection,*/ 
            int x, int y,ArrayList<Shape> tankShape,Scene scene) {
        Random rand = new Random();
        this.name = (name != null)?name:BattleField.getWarlord(rand.nextInt(BattleField.getWarlordsSize()));
        this.armor = armor;
        //this.unitsOfFire = unitsOfFire;
        //this.listOfEnemies = listOfEnemies;
        this.life = life;
        this.x = x;                         //??
        this.y = y;                         //??
        this.tankShape = tankShape;
        this.scene = scene;
        observers = new ArrayList<>();
        instance = BattleField.getInstance();
        listOfEnemies = instance.getListOfTanks();
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
    
    //public void getListOfEnemies(BattleField instance){//to PCtank
    //    this.listOfEnemies = instance.getListOfTanks();
    //}
    
    public String getHit(int hit){ //must be overriden in TankPC(Tank offender)
        int damage = this.armor - hit;
        if (damage <= 0){
            this.life += damage;
            this.armor = 0;
        }else{
            this.armor -= damage;
        }
        Text txtAmmo = (Text)this.tankShape.get(2);
        txtAmmo.setText("A: "+armor);
        Text txtHealth = (Text)this.tankShape.get(3);
        txtHealth.setText("H: "+life);
        if (this.life <= 0) this.explode();
        return "Say ouch";
    }
    
    public String gotEnemy(){//called by missle
        return "Say take it";//and display points
    }
    
    public void explode(){//it must run JavaFXView - draw red circle, then light blue, remove tankShape life = 0;
        notifyObservers(this.getX(),this.getY());
        instance.getListOfTanks().remove(this);//killing itself
    }

    @Override
    public String toString(){
        return String.format("Tank %s %nwith armor %d and health %d%n",this.name,this.armor,this.life);
    }

    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(){
        for (Observer observer : observers){
            observer.missileFly(missilePath, explodeR);
        }
    }

    public void notifyObservers(int x,int y){
        for (Observer observer : observers){
            observer.missileExplode(x,y,27);
        }
    }

    public void flyMissile(ArrayList<double[]> missilePath, double r, int yield){
        this.missilePath = missilePath;
        explodeR = r;
        checkIfHitted(yield);
        notifyObservers();
    }

    public void checkIfHitted(int yield){
        for (Tank t : listOfEnemies){
            int X = t.getX();
            int Y = t.getY();
            if ((X+7 > missilePath.get(missilePath.size()-1)[0] - explodeR)
                    && (X-7 < missilePath.get(missilePath.size()-1)[0] +explodeR)){
                System.out.println("Tank "+t.name+" zone: "+(X-7)+":"+(X+7)+
                        " missile "+(missilePath.get(missilePath.size()-1)[0] -explodeR)
                        +":"+(missilePath.get(missilePath.size()-1)[0] +explodeR));
                t.getHit(yield);
                break;
            }
        }
    }

    public abstract void shotEnemy(Tank target,double angle,double initialSpeed);
    public abstract String selectEnemy();//operates with 3 next methods and threats enemy(PC)
    public abstract int setTubeDirections(int step);
    public abstract int setShotPower(int step);
    public abstract Missile setMissle();
    
}
