/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public abstract class Missile {
    protected int weight;
    protected int imposedArea; //radius of explosion
    protected int yield; //points of hit in explosion area
    protected double angle;
    protected double initSpeed;
    protected Tank parentTank;
    protected BattleField instance = BattleField.getInstance();

    public Missile(double angle, double initSpeed, Tank parentTank){
        this.angle = angle;
        this.initSpeed = initSpeed;
        this.parentTank = parentTank;
    }

    public void setMissileOptions(int weight, int imposedArea, int yield){
        this.weight = weight;
        this.imposedArea = imposedArea;
        this.yield = yield;
    }

    public void flyMissile(ArrayList<double[]> missilePath, int imposedArea){
        parentTank.flyMissile(missilePath, imposedArea, yield);
    }

    public void /*int[][]*/ fly() {
        final double g = 9.80665;//g constant
        angle += (angle == 90) ? 5 : 0;
        boolean averse = angle > 90;
        double angleRadian = (averse) ? Math.toRadians(180 - angle) : Math.toRadians(angle);
        double length = (Math.pow(initSpeed, 2) * Math.sin(2 * angleRadian)) / g;
        double height = (Math.pow(initSpeed, 2) * Math.pow(Math.sin(angleRadian), 2)) / (2 * g);
        double time = 2 * initSpeed * Math.sin(angleRadian) / g;
        Line tube = (Line) parentTank.tankShape.get(7);
        double posX = tube.getStartX();
        double posY = tube.getStartY();
        double heightPosY = posY - height;
        double heightPosX = (averse) ? (posX + length / 2) : (posX - length / 2);
        //modified func: heightPosY - posY is reversed due to peculiarities of JavaFX coord mapping
        final double a = (posY - heightPosY) / Math.pow((posX - heightPosX), 2);//coef a in quadratic equation
        ArrayList<double[]> flyPath = new ArrayList<>();
        double y = 1;
        int x = (int)posX;
        while (y > 0) {
            if (averse) {
                y = a * Math.pow((x - heightPosX), 2) + heightPosY;
                flyPath.add(new double[]{x, y});
                if ((int)y == (int)(instance.getLandscape()[x][1])
                        || (int)y == (int)(instance.getLandscape()[x][1])+1
                        || (int)y == (int)(instance.getLandscape()[x][1])-1
                        || x == instance.getFildSizeHorizontal()-1 || y == 0
                        || y >= instance.getFildSizeVertical()-1){
                    System.out.printf("Angle=%.3f speed=%.3f posX=%s posY=%.3f hposX=%.3f hposY=%.3f X=%s Y=%.3f%n",
                            angle,initSpeed,posX,posY,heightPosX,heightPosY,x,y);
                    flyMissile(flyPath, imposedArea);
                    break;
                }
                x++;
            }else {
                y = a * Math.pow((x - heightPosX), 2) + heightPosY;
                flyPath.add(new double[]{x, y});
                if ((int)y == (int)(instance.getLandscape()[x][1])
                        || (int)y == (int)(instance.getLandscape()[x][1])+1
                        || (int)y == (int)(instance.getLandscape()[x][1])-1
                        || x == 0 || y == 0
                        || y >= instance.getFildSizeVertical()-1){
                    System.out.printf("Angle=%.3f speed=%.3f posX=%s posY=%.3f hposX=%.3f hposY=%.3f X=%s Y=%.3f%n",
                            angle,initSpeed,posX,posY,heightPosX,heightPosY,x,y);
                    flyMissile(flyPath, imposedArea);
                    break;
                }
                x--;
            }
        }
    }

}
