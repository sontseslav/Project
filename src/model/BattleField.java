/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author user
 */
public class BattleField <T extends Tank> {
    private ArrayList<Tank> listOfTanks;
    private double[][] tankCoords;
    private int tankWidth;
    private static BattleField instance;
    private double[][] landscape; //[i;y]
    private int fildSizeHorizontal;
    private int fildSizeVertical;
    
    private BattleField(int fildSizeHorizontal, int fildSizeVertical,int tankWidth) {
        this.fildSizeHorizontal = fildSizeHorizontal;
        this.fildSizeVertical = fildSizeVertical;
        this.tankWidth = tankWidth;
        this.listOfTanks = new ArrayList<>();
    }

    public int getFildSizeHorizontal() {
        return fildSizeHorizontal;
    }

    public int getFildSizeVertical() {
        return fildSizeVertical;
    }

    public double [][] getTankCoords(int tankQuantity) {
        if (landscape == null) this.createLandscape();
        if (tankCoords == null) this.createTanks(tankQuantity);
        return tankCoords;
    }

    public double[][] getLandscape() {
        if (landscape == null) this.createLandscape();
        return landscape;
    }

    public int getTankWidth() {
        return tankWidth;
    }
    
    public void addToTankList(Tank t){
        this.listOfTanks.add(t);
    }
    
    public static BattleField getInstance(int width,int hight,int tankWidth){//should be invoked first of all
        instance = new BattleField(width, hight,tankWidth);
        return instance;
    }
    
    public static BattleField getInstance(){
        return instance;
    }
    
    private double[][] createLandscape(){
        Random rand = new Random();
        landscape = new double[this.fildSizeHorizontal][2];
        /**
        * y=A*sin(w(x-a))+C 
        * C = 4.5 
        * 0 < A <= C
        * fildSizeHorizontal/3 <= P <= fildSizeHorizontal
        * 0 <= b <= P
        * a=P/4+b
        * w = 2*pi/P
        */ 
		double Ad = rand.nextDouble();
		double Pd = rand.nextDouble();
		double ad = rand.nextDouble();
		System.out.println("Vertical: "+fildSizeVertical+" Horizontal: "+fildSizeHorizontal);
        double C = fildSizeVertical/((8.0*Ad < 3)?(8.0*Ad+3):(8.0*Ad));
        double A = C*Ad;
        double P = fildSizeHorizontal/(3*Pd);
        double w = 2*Math.PI/P;
        double a = P/4+P*10000*ad/15000;
        for (int i = 0; i < fildSizeHorizontal; i++){
            landscape[i][0] = i;
            landscape[i][1] = A*Math.sin(w*(i-a)) + C +
                    + (fildSizeVertical-C*2); //moving landscape down
        }
        return landscape;
    }
    
    private double [][] createTanks(int tankQuantity) {//max = 7
        tankCoords = new double[tankQuantity][2];
        int distance = (int)fildSizeHorizontal/tankQuantity;
        for (int i = 0; i < tankQuantity; i++){
            tankCoords[i][0] = distance/2 + distance*i;
            tankCoords[i][1] = landscape[(int)tankCoords[i][0]][1]; //tank shoudn't be under the ground
        }
        // return coordinates of tanks
        return tankCoords;
    }
    
    public void startGame(){
    	listOfTanks.get(0).listOfEnemies = listOfTanks;
    	listOfTanks.get(1).listOfEnemies = listOfTanks;
    	listOfTanks.get(0).setTubeDirections(0);
    	listOfTanks.get(0).shotEnemy(null);
    	listOfTanks.get(1).setTubeDirections(0);
    	listOfTanks.get(1).shotEnemy(null);
    }
   
}
