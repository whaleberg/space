/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;


import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import vector.Point2d;
import vector.Vector2d;
/**
 *
 * @author sailfish
 */
public class Ship implements Active, Positionable{
    private Node node;
    private Point2d position;
    private Vector2d velocity;
    
    public Ship(Node node, Point2d position, Vector2d velocity){
        this.node = node;
        this.position = new Point2d(position);
        this.velocity = new Vector2d(velocity);
        this.relocate(this.position);
       
    }
    
    public Node getNode(){
        return node;
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
        node.relocate(newPosition.getX(),newPosition.getY());
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
    
    

    


    private StringProperty positionString = new SimpleStringProperty();
    public ReadOnlyStringProperty positionStringProperty() { return positionString; }
    public String getPositionString() { return positionString.get(); }
    private void setPositionString(String value) { positionString.set(value); }

}
