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
    private final int tankWidth = 3;
    private static BattleField instance;
    private double[][] landscape; //[i;y]
    private int fildSizeHorizontal;
    private int fildSizeVertical;
    
    private BattleField(int fildSizeHorizontal, int fildSizeVertical) {
        this.fildSizeHorizontal = fildSizeHorizontal;
        this.fildSizeVertical = fildSizeVertical;
        listOfTanks = new ArrayList<>();
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
    
    
    
    public static BattleField getInstance(int width,int hight){//should be invoked first of all
        instance = new BattleField(width, hight);
        return instance;
    }
    
    private double[][] createLandscape(){
        Random rand = new Random();
        landscape = new double[this.fildSizeHorizontal][2];
        /**
        * y=A*sin(w(x-a))+C 
        * C = 4.5 
        * 0 < A <= C
        * a=P/4+b
        * fildSizeHorizontal/3 <= P <= fildSizeHorizontal*10
        * 0 <= b <= P
        * w = 2*pi/P
        */ 
        final double C = 4.5;
        double preA = rand.nextInt(45*10000)/100000;
        double A = (preA == 0) ? 0.00001 : preA;
        double P = (rand.nextInt(319000000))/300000+36.66666;
        //double P = (rand.nextInt(16555555))/100000+5.55555;
        double w = 2*Math.PI/P;
        double a = P/4 + rand.nextInt((int)Math.ceil(P*10000))/1100000;
        for (int i = 0; i < fildSizeHorizontal; i++){
            landscape[i][0] = i;
            landscape[i][1] = A*Math.sin(w*(i-a)) + C +
                    + (fildSizeVertical-C*2); //moving landscape down
            //System.out.printf("%s) As is: %s: %s;%n",i,landscape[i][1]);
        }
        return landscape;
    }
    
    private double [][] createTanks(int tankQuantity) {//max = 7
        tankCoords = new double[tankQuantity][2];
        //Tank t = null;
        int distance = (int)fildSizeHorizontal/tankQuantity;
        for (int i = 0; i < tankQuantity; i++){
            tankCoords[i][0] = distance/2 + distance*i;
            tankCoords[i][1] = landscape[(int)tankCoords[i][0]][1]; //tank shoudn't be under the ground
            //t = new TankHuman(null, 0, 100, tankCoords[i][0], tankCoords[i][1]);
            //listOfTanks.add(t);
        }
        // return coordinates of tanks
        return tankCoords;
    }
   
}
