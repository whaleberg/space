/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.beans.property.ReadOnlyObjectProperty;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public interface Positionable {

    public Point2d getPos();
    public ReadOnlyObjectProperty<Point2d> posProperty();
    public Point2d getPositionIn(double seconds);

}
