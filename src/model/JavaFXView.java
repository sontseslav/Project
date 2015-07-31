package model;/**
 * Created by coder on 25.07.15.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class JavaFXView extends Application implements Observer{
    private static final int fieldWidth = 770;
    private static final int fieldHeight = 550;
    private static final int tankWidth = 18;
    private static BattleField instance;
    private static int tankQuantity;
    private Group root;
    private Canvas canvas;
    private Scene scene;
    private Line speed;
    private Text speedDecimal;
    private Random rand;
    private ArrayList<Shape> tankShape;//needed?
    private static TubeRotationStateManager manager;
    protected double[][] landscape;
    protected double[][] tankCoords;

    public static void startJavaFX(String[] args, int tankQ) {
        if (tankQ > fieldWidth/(tankWidth*3)){
            System.out.println("Too many tanks");
            return;
        }
        tankQuantity = tankQ;
        instance = BattleField.getInstance(fieldWidth, fieldHeight,tankWidth);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        rand = new Random();
        landscape = instance.getLandscape();
        root = new Group();
        canvas = new Canvas(fieldWidth, fieldHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        scene = new Scene(root,fieldWidth, fieldHeight, Color.LIGHTBLUE);
        drawLandscape(gc);
        drawTanks(tankQuantity);
        drawSpeedBar();
        primaryStage.setTitle("JATanks");
        primaryStage.setMaxHeight(fieldHeight + 25);
        primaryStage.setMinHeight(fieldHeight + 25);
        primaryStage.setMaxWidth(fieldWidth);
        primaryStage.setMinWidth(fieldWidth);
        primaryStage.setScene(scene);
        primaryStage.show();

        manager = new TubeRotationStateManager();
        //turn started while tank quantity > 1
        for(int i = 0; i < instance.getListOfTanks().size();i++) {
            TubeRotationState state = new TubeRotationState(scene, (Line)instance.getListOfTanks().get(i).tankShape.get(7),
                    speed, speedDecimal, instance.getListOfTanks().get(i));
            manager.addState(state);//cycle for tank quantity | not tube - tank => tank instance in Tank
        }
        manager.runNextState();
        /*for(Tank tank : instance.getListOfTanks()){//if robots present
            tank.getListOfEnemies(instance);
        }*/

        /*while (instance.getListOfTanks().size() > 1){
            //new turn
            for(int i = 0;i < instance.getListOfTanks().size();i++){
                TankHuman t = (TankHuman)instance.getListOfTanks().get(i);//only humans
                t.getState().start();//rotate
                //t.shotEnemy();
            }
        }*/
    }

    public void drawLandscape(GraphicsContext gc) {//unchangeable
        gc.setStroke(Color.GREENYELLOW);
        for(int x = 0; x < fieldWidth; x++){
            gc.strokeLine(x, fieldHeight, x, landscape[x][1]);
        }
    }

    private void drawSpeedBar(){
        Rectangle bar = new Rectangle(10,10,120,30);
        bar.setFill(Color.TRANSPARENT);
        bar.setStroke(Color.BLACK);
        Text label = new Text(15,25,"Missile velocity");
        label.setFont(Font.font("Verdana", 10));
        label.setFill(Color.BLACK);
        int initialSpeed = rand.nextInt(100);
        speed = new Line(20,32,20+initialSpeed,32);
        speedDecimal = new Text(105,25,""+((int)(speed.getEndX() - speed.getStartX())));
        speedDecimal.setFont(Font.font("Verdana", 10));
        speed.setStrokeWidth(3);
        speed.setStroke(Color.GREEN);
        speed.setFill(Color.GREEN);
        root.getChildren().add(speed);
        root.getChildren().add(speedDecimal);
        root.getChildren().add(label);
        root.getChildren().add(bar);
        return;
    }

    public void drawTanks(int tankQuantity) {
        tankCoords = instance.getTankCoords(tankQuantity);
        for(int i = 0; i < tankQuantity;i++){
            int x = (int)tankCoords[i][0];//adjust to the ground
            int y = (int)tankCoords[i][1] - 2;
            tankShape = this.setTank(x, y);
            TankHuman t = new TankHuman(null, 0, 100, x, y,tankShape,scene);
            t.addObserver(this);//adding observer
            Text txt = (Text)tankShape.get(1);
            txt.setText(t.name);
            txt = (Text)tankShape.get(2);
            txt.setText("A: "+t.armor);
            txt = (Text)tankShape.get(3);
            txt.setText("H: "+t.life);
            instance.addToTankList(t);
        }
    }

    /**
     *
     * @param centerX adjusted center (X) of Track
     * @param centerY adjusted center (Y) of Track
     * @return ArrayLis(Shape) contains shapes of Tank in order:
     * <ol>
     *     <li>placeHolder</li>
     *     <li>name</li>
     *     <li>ammo</li>
     *     <li>health</li>
     *     <li>innerTrack</li>
     *     <li>outerTrack</li>
     *     <li>turret</li>
     *     <li>tube</li>
     * </ol>
     */
    private ArrayList<Shape> setTank(final int centerX,final int centerY){
        final int hTrack = 4;
        final int wTrack = hTrack*3;
        final int rTurret = 4;
        final int holderH = 50;
        final int holderW = 45;
        tankShape = new ArrayList<>();
        Rectangle tankPlace = new Rectangle(centerX-holderW/2-2,centerY-holderH+1.5*hTrack,holderW,holderH);
        tankPlace.setStroke(Color.DARKVIOLET);
        tankPlace.setFill(Color.TRANSPARENT);
        tankShape.add(tankPlace);
        Text name = new Text(centerX-holderW/2-2,centerY+15,"");
        name.setFont(Font.font("Verdana",10));
        tankShape.add(name);
        Text ammo = new Text(centerX-holderW/2,centerY-holderH*0.6,"");
        ammo.setFont(Font.font("Verdana", 8));
        tankShape.add(ammo);
        Text health = new Text(centerX+holderW/2-20,centerY-holderH*0.6,"");
        health.setFont(Font.font("Verdana",8));
        tankShape.add(health);
        Ellipse innerTrack = new Ellipse(centerX,centerY,wTrack,hTrack);
        innerTrack.setFill(Color.GREEN);
        tankShape.add(innerTrack);
        Ellipse outerTrack = new Ellipse(centerX,centerY,wTrack+1.5,hTrack+1.5);
        outerTrack.setStroke(Color.GREEN);
        outerTrack.setFill(Color.TRANSPARENT);
        tankShape.add(outerTrack);
        Circle turret = new Circle(centerX,centerY-1.5*rTurret,rTurret);
        turret.setFill(Color.GREEN);
        tankShape.add(turret);
        Line tube = new Line(centerX, centerY-1.85*rTurret, centerX+13,centerY-1.85*rTurret);
        tube.setStrokeWidth(2);
        tube.setStroke(Color.BLACK);
        tube.getTransforms().add(new Rotate(-rand.nextInt(180), centerX, centerY - 1.85*rTurret));
        tankShape.add(tube);
        root.getChildren().addAll(tankShape);
        return tankShape;
    }

    public void drawTankExplode(Tank tank) {
        // TODO Auto-generated method stub

    }

    @Override
    public void missileExplode(double x, double y, double r) {
        for (Tank t : instance.getListOfTanks()){ //is this any tank explosion?
            if(t.getX() == (int)x && t.getY() == (int)y){
                for (int i = 0; i < t.tankShape.size();i++){
                    root.getChildren().remove(t.tankShape.get(i));
                }
                break;
            }
        }
        Circle explosion = new Circle(x,y,r);
        explosion.setFill(Color.RED);
        root.getChildren().add(explosion);
    }
}
