/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import model.JavaFXView;

/**
 *
 * @author user
 */
public class Main {//move javafx to thread
    public static void main(String[] args) {
        //AbstractViewer absview = new Console();//size in chars+tankWidth
        //absview.drawLandscape();
        //absview.drawTanks(5);//12 max

        JavaFXView.startJavaFX(args,5);//14 max
        //JavaFX.startJavaFX(10);
    }
}