/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Tank;

/**
 *
 * @author user
 */
public class Console extends AbstractViewer{

    @Override
    public void drawLandscape() {
        //StringBuilder sb = new StringBuilder();
        for (int i = 0; i < instance.getFildSizeVertical();i++){
            for (int j = 0; j < instance.getFildSizeHorizontal();j++){
                if (i == this.round(landscape[j][1]) && j == (int)landscape[j][0]){
                    battleField[i][j] = 'X';
                }else{
                    battleField[i][j] = ' ';
                }
            }
            //sb.append(battleField[i]);
            //sb.append("\r\n");
        }
        //System.out.println(sb.toString());
    }

    @Override
    public void drawTanks(int tankQuantity) {
       tankCoords = instance.getTankCoords(tankQuantity);
       int x,y = 0;
       for(int i = 0; i < tankQuantity;i++){
           x = (int)tankCoords[i][0];
           y = round(tankCoords[i][1])-1;
           battleField[y][x]='O';
           battleField[y][x+1]='O';
           battleField[y][x-1]='O';
           battleField[y-1][x]='o';
           battleField[y-1][x+1]='-';
           showBattleField();
       }
    }

    @Override
    public void drawTankExplode(Tank tank) {
        int x = tank.getX();
        int y = tank.getY();
        int r = instance.getTankWidth();
        //drawCircle(x,y,r);
        //modifyLandscape(x,y,r);
    }
    
    private int round(double x){
        x = (x % 1 > 0.5) ? x+1 : x;
        return (int)x;
    }
    
    private void showBattleField(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < instance.getFildSizeVertical();i++){
            sb.append(battleField[i]);
            sb.append("\r\n");
        }
        System.out.println(sb.toString());
    }
}
