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
public class BattleField {
    private ArrayList<Tank> listOfTanks;
    private int[][] tankCoords;
    private final int tankWidth = 3;
    private static final BattleField instance = new BattleField(110,36);//size in chars
    private int[][] landscape; //[i;y]
    private int fildSizeHorizontal;
    private int fildSizeVertical;
    
    private BattleField(int fildSizeHorizontal, int fildSizeVertical) {
        this.fildSizeHorizontal = fildSizeHorizontal;
        this.fildSizeVertical = fildSizeVertical;
    }

    public int getFildSizeHorizontal() {
        return fildSizeHorizontal;
    }

    public int getFildSizeVertical() {
        return fildSizeVertical;
    }

    public int[][] getTankCoords() {
        if (landscape == null) this.createLandscape();
        if (tankCoords == null) this.createTanks(tankWidth);
        return tankCoords;
    }

    public int[][] getLandscape() {
        if (landscape == null) this.createLandscape();
        return landscape;
    }

    public int getTankWidth() {
        return tankWidth;
    }
    
    
    
    public static BattleField getInstance(){//should be invoked first of all
        return instance;
    }
    
    private int[][] createLandscape(){
        Random rand = new Random();
        landscape = new int[this.fildSizeHorizontal][2];
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
        double w = 2*Math.PI/P;
        double a = P/4 + rand.nextInt((int)Math.ceil(P*10000))/1100000;
        for (int i = 0; i < fildSizeHorizontal; i++){
            landscape[i][0] = i;
            landscape[i][1] = this.round(A*Math.sin(w*(i-a)) + C + 27);
            //System.out.printf("%s) As is: %s, rounded: %s, casted to int: %s;%n",i,
            //        A*Math.sin(w*(i-a))+C+27,
            //        this.round(A*Math.sin(w*(i-a)) + C +27),
            //        landscape[i][1]);
        }
        return landscape;
    }
    
    private int round(double x){
        x = (x % 1 > 0.5) ? x+1 : x;
        return (int)x;
    }
    
    private int[][] createTanks(int tankQuantity) {//max = 7
        tankCoords = new int[tankQuantity][2];
        int distance = (int)fildSizeHorizontal/(tankQuantity*tankWidth);
        for (int i = 0; i < tankQuantity; i++){
            tankCoords[i][0] = distance*(1+i) + tankWidth*i + tankWidth/2;//center of tank
            tankCoords[i][1] = landscape[tankCoords[i][0]][1];
        }
        // return coordinates of tanks
        return tankCoords;
    }
    
   
}
