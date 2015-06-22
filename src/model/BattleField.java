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
public class BattleField {
    private ArrayList<Tank> listOfTanks;
    private static final BattleField instance = new BattleField();
    private int[][] landscape;
    
    private BattleField() {}
    
    public static BattleField getInstance(){//should be invoked first of all
        return instance;
    }
    
    public int[][] drawLandscape(){
        //sinusoid
        return landscape;
    }
    
    public void createTanks(int tankQuantity) {
        for (int i = 0; i < tankQuantity; i++){
            //accodring to landscape create tanc coordinates
        }
    }
    
    public int[][] drawTanks(){
        // return coordinates of tanks
        return null;
    }
}
