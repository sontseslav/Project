/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 *
 * @author user
 */
public class TankHuman extends Tank{
	
	private int count;
	boolean done;

    public TankHuman(String name, int armor, /*HashMap<String,Integer> unitsOfFire, 
            ArrayList<Tank> listOfEnemies,*/ int life, /*int tubeDirection, */
            int x, int y,Line tube,Scene scene) {
        super(name, armor, /*unitsOfFire, listOfEnemies,*/ life, /*tubeDirection,*/ x, y,tube,scene);
    }
    
    @Override
    public void shotEnemy(Tank target) {//move to pc
        
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
    	final int STEP = 5;
		count = 0;
		System.out.println(tube.getEndX()+":"+tube.getEndY()+":"+tube.getRotate());
		tube.setOnKeyPressed(new EventHandler<KeyEvent>(){
		//scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if (done) return;
				switch(event.getCode()){
					case UP:
						System.out.println("UP");
						tube.getTransforms().add(new Rotate(-STEP, tube.getStartX(), tube.getStartY()));
						count++;
						break;
					case DOWN:
						System.out.println("DOWN");
						tube.getTransforms().add(new Rotate(STEP, tube.getStartX(), tube.getStartY()));
						count--;
						break;
					case ENTER:
						System.out.println("DONE");
						done = true;
						break;
				}
			}
		});
		System.out.println(count);
		return count;
	}

    @Override
    public int setShotPower(int step) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Missle setMissle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
