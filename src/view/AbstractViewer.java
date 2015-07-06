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
    protected BattleField instance;
    protected char[][] battleField;
    protected double[][] landscape;
    protected double[][] tankCoords;

    public AbstractViewer() {
        this.instance = BattleField.getInstance(110,36);//size in chars
        battleField = 
            new char[instance.getFildSizeVertical()][instance.getFildSizeHorizontal()];
        landscape = instance.getLandscape();
    }
    
    public abstract void drawLandscape();
    public abstract void drawTanks(int tankQuantity);
    public abstract void drawTankExplode(Tank tank);
    
    
}
