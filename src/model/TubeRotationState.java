package model;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class TubeRotationState {
    private final static int STEP = 2;
    private final Scene scene;
    private final Line tube;
    private Line speed;
    private double angle;
    private double initialSpeed;
    private Tank tank;
    private TubeRotationStateManager manager;

    public TubeRotationState(Scene scene, Line tube,Line speed, double initialSpeed, Tank tank) {
        this.scene = scene;
        this.tube = tube;
        this.speed = speed;
        this.initialSpeed = initialSpeed;
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
            public void handle(KeyEvent event) {//Check if we are TankHuman => tank = (TankHuman)tank
                if(!tank.isAlive()){
                    scene.setOnKeyPressed(null);
                    System.out.println(tank.name+" gone...");
                    return;
                }
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
                        initialSpeed = speed.getEndX();
                        if (initialSpeed < 100) {
                            initialSpeed++;
                            speed.setEndX(initialSpeed);
                        }
                        break;
                    case DOWN:
                        initialSpeed = speed.getEndX();
                        if (initialSpeed - speed.getStartX() > 0) {
                            initialSpeed--;
                            speed.setEndX(initialSpeed);
                        }
                        break;
                    case ENTER:
                        /*double x0 = tube.getStartX();
                        double y0 = tube.getStartY();
                        double x1 = tube.getEndX();
                        double y1 = tube.getEndY();
                        System.out.println(x0+":"+y0+";"+x1+":"+y1);
                        Point2D lTP = tube.localToParent(tube.getEndX(),tube.getEndY());
                        double x2 = lTP.getX();
                        double y2 = lTP.getY();
                        System.out.println(x2+":"+y2);
                        if(x0<x2){
                            angle = Math.toDegrees(Math.atan((y1 - y2)/(x2 - x0)));
                        }else if(x2<x0){
                            angle = Math.toDegrees(Math.atan((y1 - y2)/(x0 - x2)));
                        }else{
                            angle = 90;
                        }*/
                        Point2D lTP = tube.localToParent(tube.getEndX(),tube.getEndY());
                        double x0 = tube.getStartX();
                        double y0 = tube.getStartY();
                        double x1 = lTP.getX();
                        double y1 = lTP.getY();
                        angle = Math.toDegrees(Math.atan2((y0 - y1),(x0 - x1)));
                        angle = (angle > 90)?(180 - angle):angle;//! remove to preserve direction !
                        initialSpeed -= speed.getStartX();
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
            TubeRotationState state = new TubeRotationState(scene,tube,speed,initialSpeed,tank);
            manager.addState(state);
        }
        manager.onStateFinished();
    }
}
