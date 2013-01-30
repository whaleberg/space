/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;


import com.google.common.collect.ImmutableMap;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vector.Point2d;
import vector.Vector2d;
/**
 *
 * @author sailfish
 */
public class Ship implements Active, Drawable{

    private Point2d position;
    private Vector2d velocity;
    
    private final String ID;
    
    public static final IconType icon = IconType.SHIP;
    
    
    
    public Ship(Point2d position, Vector2d velocity){

        this.position = new Point2d(position);
        this.velocity = new Vector2d(velocity);
        this.relocate(this.position);
        this.ID = "Ship:"+StaticUtility.createID();
    }
    

    
    public String getPositionAsAString(){
        return position.getX() +"/" + position.getY();
    }
    
    
    @Override
    public void age(long seconds){
        this.travel(seconds);
    }
    
    private void travel(long seconds){
       Point2d newPos = this.position.translate(velocity.scale(seconds));
      
       relocate(newPos);
    }
    
    public final void relocate(Point2d newPosition){
        position = newPosition;
        setPositionString(getPositionAsAString());
    }

    @Override
    public Point2d getPos() {
        return new Point2d(this.position);
    }
    @Override
    public String toString(){
        return this.getPositionAsAString();
    }
    
    

       @Override
    public String getID(){
        return ID;
    }


    private StringProperty positionString = new SimpleStringProperty();
    public ReadOnlyStringProperty positionStringProperty() { return positionString; }
    public String getPositionString() { return positionString.get(); }
    private void setPositionString(String value) { positionString.set(value); }

    @Override
    public IconType getIconType() {
        return icon;
    }

    @Override
    public ImmutableMap<String, Double> getDrawingParameters() {
       return ImmutableMap.of();
    }

}
