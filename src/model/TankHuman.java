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
public class TankHuman extends Tank{

    public TankHuman(String name, int armor, ArrayList<Missle> unitsOfFire, 
            ArrayList<Tank> listOfEnemies, int life, int tubeDirection, 
            int x, int y) {
        super(name, armor, unitsOfFire, listOfEnemies, life, tubeDirection, x, y);
    }

    @Override
    public void shotEnemy(Tank target) {
        
    }

    @Override
    public String selectEnemy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int setTubeDirections(int step) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
