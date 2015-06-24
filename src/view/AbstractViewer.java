/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.BattleField;
import model.Tank;

/**
 *
 * @author user
 */
public abstract class AbstractViewer {
    protected BattleField instance = BattleField.getInstance();
    protected char[][] battleField = 
            new char[instance.getFildSizeVertical()][instance.getFildSizeHorizontal()];
    protected double[][] landscape = instance.getLandscape();
    
    public abstract void drawLandscape();
    public abstract void drawTanks();
    public abstract void drawTankExplode(Tank tank);
    
    
}
