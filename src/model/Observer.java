package model;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

/**
 * Created by coder on 30.07.15.
 */
public interface Observer {
    void missileFly(ArrayList<double[]> missilePath, double r);
    void missileExplode(double x,double y, double r);
}
