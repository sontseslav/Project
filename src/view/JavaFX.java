package view;

import java.util.Random;

import model.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class JavaFX extends Application{
    private static final int fieldWidth = 770;
    private static final int fieldHight = 550;
    private static final int tankWidth = 18;//?
    private static BattleField instance;
    private static int tankQuantity;
    private Group root;
    private Scene scene;
    private Random rand;
    //private Line tube;
    private int count;
	private static TubeRotationStateManager manager;
    protected double[][] landscape;
    protected double[][] tankCoords;
    
	public static void startJavaFX(int tankQ) {
	//public static void main(String[] args) {
		if (tankQ > fieldWidth/(tankWidth*3)){
			System.out.println("Too many tanks");
			return;
		}
		tankQuantity = tankQ;
		instance = BattleField.getInstance(fieldWidth,fieldHight,tankWidth);
		Application.launch(new String[1]);//arg provides nothing
	}

	public static TubeRotationStateManager getManager(){
		return manager;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {/*
		rand = new Random();
		landscape = instance.getLandscape();
		root = new Group();
		Canvas canvas = new Canvas(fieldWidth,fieldHight);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		scene = new Scene(root,fieldWidth,fieldHight,Color.LIGHTBLUE);
		this.drawLandscape(gc);
		this.drawTanks(tankQuantity, gc);
		primaryStage.setTitle("JATanks");
		primaryStage.setMaxHeight(fieldHight + 25);
		primaryStage.setMinHeight(fieldHight + 25);
		primaryStage.setMaxWidth(fieldWidth);
		primaryStage.setMinWidth(fieldWidth);
		primaryStage.setScene(scene);
		primaryStage.show();

		//instance.startGame();

		manager.runNextState();
	*/}
	/*
	public void drawLandscape(GraphicsContext gc) {
		gc.setStroke(Color.GREENYELLOW);
		for(int x = 0; x < fieldWidth; x++){
			gc.strokeLine(x, fieldHight, x, landscape[x][1]);
		}
	}
	
	public void drawTanks(int tankQuantity,GraphicsContext gc) {
		tankCoords = instance.getTankCoords(tankQuantity);
		manager = new TubeRotationStateManager();
		for(int i = 0; i < tankQuantity;i++){
			int x = (int)tankCoords[i][0] - 10;
			int y = (int)tankCoords[i][1] - 2;
			Line tube = this.setTank(x, y, gc);
			instance.addToTankList(new TankHuman(null, 0, 100, x, y,tube,scene));
			manager.addState(new TubeRotationState(scene, tube));
		}
		
	}
	
	private Line setTank(int centerX,int centerY, GraphicsContext gc){
		int rWheel = 5;
		int hTrack = rWheel;
		int wTrack = rWheel*3;
		int rTurret = 7;
		gc.setFill(Color.GREEN);//create OBJECT not a forms. It is needed to join many objects to one
		gc.setStroke(Color.GREEN);
		gc.fillOval(centerX, centerY, wTrack, hTrack);
		gc.strokeOval(centerX - 1.5, centerY - 1.5, wTrack + 3, hTrack + 3);
		gc.fillOval(centerX+wTrack/4.5, centerY-rTurret-2, rTurret, rTurret);
		Line tube = new Line(centerX+rTurret, centerY-rTurret+1, centerX+rTurret+10,centerY-rTurret+1);
		tube.setStrokeWidth(2);
		tube.setStroke(Color.BLACK);
		tube.getTransforms().add(new Rotate(-rand.nextInt(180), centerX+rTurret, centerY-rTurret+1));
		root.getChildren().add(tube);//add not a tube, but a tank!
		return tube;
	}

	public void drawTankExplode(Tank tank) {
			// TODO Auto-generated method stub
			
	}
	*/
}