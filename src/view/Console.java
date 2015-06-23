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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < instance.getFildSizeVertical();i++){
            for (int j = 0; j < instance.getFildSizeHorizontal();j++){
                if (i == landscape[j][1] && j == landscape[j][0]){
                    battleField[i][j] = 'X';
                }else{
                    battleField[i][j] = ' ';
                }
            }
            sb.append(battleField[i]);
            sb.append("\r\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public void drawTanks() {
        
    }

    @Override
    public void drawTankExplode(Tank tank) {
        int x = tank.getX();
        int y = tank.getY();
        int r = instance.getTankWidth();
        //drawCircle(x,y,r);
        //modifyLandscape(x,y,r);
    }
    
}
