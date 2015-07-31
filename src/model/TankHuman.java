/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.shape.Shape;

/**
 *
 * @author user
 */
public class TankHuman extends Tank{
	private TubeRotationState state;
	private int count;
	boolean done;

    public TankHuman(String name, int armor, /*HashMap<String,Integer> unitsOfFire, 
            ArrayList<Tank> listOfEnemies,*/ int life, /*int tubeDirection, */
            int x, int y,ArrayList<Shape> tankShape,Scene scene) {
        super(name, armor, /*unitsOfFire, listOfEnemies,*/ life, /*tubeDirection,*/ x, y,tankShape,scene);
    }

    public TubeRotationState getState() {
        return state;
    }

    public void setState(TubeRotationState state) {
        this.state = state;
    }

    @Override
    public void shotEnemy(Tank target,double angle,double initialSpeed) {//target always null add weapon in args
        SmallMissile missile = new SmallMissile(angle,initialSpeed,this);
        missile.fly();
    }

    public void shotEnemy(){//should take coords from tube!
    	System.out.println("Babah!");
    }

    @Override
    public String selectEnemy() {//move to pc
        return null;
    }

    @Override
    public int setTubeDirections(int step) {
    	int count = 0;

		return count;
	}

    @Override
    public int setShotPower(int step) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Missile setMissle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
