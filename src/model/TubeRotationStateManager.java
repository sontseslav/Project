package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by coder on 24.07.15.
 */
public class TubeRotationStateManager {
    private Queue<TubeRotationState> stateQueue = new LinkedList<>();

    //public Queue<TubeRotationState> getStateList(){
    //    return this.stateQueue;
    //}

    public void addState(TubeRotationState state){
        state.setManager(this);
        stateQueue.add(state);
    }

    public void runNextState(){
        TubeRotationState state = stateQueue.poll();
        if (state != null){
            state.start();
        }

        //for(int i = 0; i < stateQueue.size(); i++){
        //    stateQueue.get(i).start();
        //}
    }

    public void onStateFinished(){
        System.out.println("Next tank");
        runNextState();
    }
}
