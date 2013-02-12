/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import vector.Point2d;
import vector.Vector2d;

/**
 *
 * @author sailfish
 */
public class ReadOnlyPoint2dWrapper extends ReadOnlyObjectWrapper<Point2d>{

    public ReadOnlyPoint2dWrapper() {
    }

    public ReadOnlyPoint2dWrapper(Point2d t) {
        super(t);
    }

    public ReadOnlyPoint2dWrapper(Object o, String string) {
        super(o, string);
    }

    public ReadOnlyPoint2dWrapper(Object o, String string, Point2d t) {
        super(o, string, t);
    }
    
    public ObjectBinding<Point2d> translate(final Vector2d vect){
        return new ObjectBinding<Point2d>() {

            @Override
            protected Point2d computeValue() {
                return ReadOnlyPoint2dWrapper.this.get().translate(vect);
            }
        };
        
    }
    
       public ObjectBinding<Point2d> translate(final ObjectBinding<Vector2d> vectbinding){
        return new ObjectBinding<Point2d>() {
            {
               bind(vectbinding);
            }
            
            @Override
            protected Point2d computeValue() {
                return ReadOnlyPoint2dWrapper.this.get().translate(vectbinding.get());
            }
        };
        
    }

}
