package model;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class TubeRotationState {
    private final static int STEP = 2;
    private final Scene scene;
    private final Line tube;
    private Text speedDecimal;
    private Line speed;
    private double angle;
    private int initialSpeed;
    private Tank tank;
    private TubeRotationStateManager manager;

    public TubeRotationState(Scene scene, Line tube,Line speed, Text speedDecimal, Tank tank) {
        this.scene = scene;
        this.tube = tube;
        this.speedDecimal = speedDecimal;
        this.speed = speed;
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(TankHuman tank) {
        this.tank = tank;
    }

    public void setManager(TubeRotationStateManager manager){
        this.manager = manager;
    }


    public void start(){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(!tank.isAlive()){
                    scene.setOnKeyPressed(null);
                    System.out.println(tank.name+" gone...");
                    finish();
                    return;
                }
                //Check if we are TankHuman => tank = (TankHuman)tank
                initialSpeed = Integer.parseInt(speedDecimal.getText());
                switch (event.getCode()){
                    case LEFT:
                        /*Point2D leftPosition = tube.localToParent(tube.getEndX(),tube.getEndY());
                        System.out.println(leftPosition.getY() +":"+ tube.getStartY());
                        if(leftPosition.getY() <= tube.getStartY()) {
                        */
                            tube.getTransforms().add(new Rotate(-STEP, tube.getStartX(), tube.getStartY()));
                        /*}
                        tube.setEndY(tube.getStartY()+1);
                        System.out.println("Stuck");
                        */
                        break;
                    case RIGHT:
                        /*Point2D rightPosition = tube.localToParent(tube.getEndX(),tube.getEndY());
                        System.out.println(rightPosition.getY() +":"+ tube.getStartY());
                        if(rightPosition.getY() <= tube.getStartY()) {
                        */
                            tube.getTransforms().add(new Rotate(STEP, tube.getStartX(), tube.getStartY()));
                        /*
                        }
                        tube.setEndY(tube.getStartY()+1);
                        System.out.println("Stuck");
                        */
                        break;
                    case UP:
                        //Point2D toUP = speed.localToParent(speed.getEndX(),speed.getEndY());
                        //initialSpeed = toUP.getX()-speed.getStartX();
                        initialSpeed = Integer.parseInt(speedDecimal.getText());
                        if (initialSpeed < 100) {
                            initialSpeed++;
                            speed.setEndX(initialSpeed+speed.getStartX());
                            speedDecimal.setText(initialSpeed+"");
                        }
                        break;
                    case DOWN:
                        //Point2D toDown = speed.localToParent(speed.getEndX(),speed.getEndY());
                        //initialSpeed = toDown.getX()-speed.getStartX();
                        initialSpeed = Integer.parseInt(speedDecimal.getText());
                        if (initialSpeed > 0) {
                            initialSpeed--;
                            speed.setEndX(initialSpeed+speed.getStartX());
                            speedDecimal.setText(initialSpeed + "");
                        }
                        break;
                    case ENTER:
                        Point2D lTP = tube.localToParent(tube.getEndX(),tube.getEndY());
                        double x0 = tube.getStartX();
                        double y0 = tube.getStartY();
                        double x1 = lTP.getX();
                        double y1 = lTP.getY();
                        angle = Math.toDegrees(Math.atan2((y0 - y1),(x0 - x1)));
                        //angle = (angle > 90)?(180 - angle):angle;//! remove to preserve direction !
                        //initialSpeed -= speed.getStartX();
                        tank.shotEnemy(null,angle,initialSpeed);
                        finish();//final{}
                        return;
                    default:
                        break;
                }
            }
        });
    }

    public void finish(){
        //Kill current
        scene.setOnKeyPressed(null);
        //set new class
        //getTank().setState(new TubeRotationState(scene,tube));
        if(tank.isAlive()){
            TubeRotationState state = new TubeRotationState(scene,tube,speed,speedDecimal,tank);
            manager.addState(state);
        }
        manager.onStateFinished();
    }
}
