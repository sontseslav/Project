/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class SmallMissile extends Missile {

    final int weight = 1;
    final int imposedArea = 10;
    final int yield = 10;

    public SmallMissile(double angle, double initSpeed, Tank parentTank) {
        super(angle,initSpeed,parentTank);
        super.setMissileOptions(weight,imposedArea,yield);
    }
/*
    @Override
    public void flyMissile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/

    /*@Override
    public void fly() {
        super.fly();
    }
*/
}
