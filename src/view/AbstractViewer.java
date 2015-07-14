/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.BattleField;
import model.Tank;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public abstract class AbstractViewer{
    protected BattleField instance;
    protected char[][] battleField;
    protected double[][] landscape;
    protected double[][] tankCoords;

    public AbstractViewer(int width,int hight,int tankWidth) {
        this.instance = BattleField.getInstance(width,hight,tankWidth);//size in chars
    }
    
    public abstract void drawLandscape();
    public abstract void drawTanks(int tankQuantity);
    public abstract void drawTankExplode(Tank tank);
    
    
}
