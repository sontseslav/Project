package model;

/**
 * Created by coder on 30.07.15.
 */
public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}
