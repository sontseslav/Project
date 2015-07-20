/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import model.BattleField;
import model.Tank;
import view.AbstractViewer;
import view.Console;
import view.JavaFX;

/**
 *
 * @author user
 */
public class Main {//move javafx to thread
    public static void main(String[] args) {
        //AbstractViewer absview = new Console();//size in chars+tankWidth
        //absview.drawLandscape();
        //absview.drawTanks(5);//12 max
    	Runnable r = new JavaFXThread();
    	Thread thread = new Thread(r);
    	thread.start();
    	//while(BattleField.getInstance() == null){}//wait BattleField to init
    	//BattleField bf = BattleField.getInstance();
    	//bf.startGame();
    }
}
class JavaFXThread implements Runnable{

	@Override
	public void run() {
		JavaFX.startJavaFX(2);//14 tanks max
	}
	
}