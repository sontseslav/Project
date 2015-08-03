/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import view.InitialJFX;

/**
 *
 * @author user
 */
public class Main {//move javafx to thread
    private static int tankQuantity;
    private static boolean isDatabaseSet;

    public static void main(String[] args) throws Exception{
        //AbstractViewer absview = new Console();//size in chars+tankWidth
        //absview.drawLandscape();
        //absview.drawTanks(5);//12 max
        InitialJFX.startInitialJFX(args);
    }

    public static void setTankQuantity(int tankQuantity) {
        Main.tankQuantity = tankQuantity;
    }

    public static void setIsDatabaseSet(boolean isDatabaseSet) {
        Main.isDatabaseSet = isDatabaseSet;
    }

    public static boolean getDatabaseSet() {
        return isDatabaseSet;
    }

    public static int getTankQuantity() {
        return tankQuantity;
    }
}