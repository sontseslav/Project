/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import view.AbstractViewer;
import view.Console;

/**
 *
 * @author user
 */
public class Main {
    public static void main(String[] args) {
        AbstractViewer absview = new Console();
        absview.drawLandscape();
        absview.drawTanks(10);//10 max
    }
}
