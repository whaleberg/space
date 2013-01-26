/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;


import javafx.scene.Node;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
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
    
    public String getPositionString(){
        return node.getLayoutX() +"/" + node.getLayoutY();
    }
    
    
    @Override
    public void age(long seconds){
        this.travel(seconds);
    }
    
    private void travel(long seconds){
       this.position.scaleAdd(seconds,this.velocity,this.position);
       relocate(position);
    }
    
    public final void relocate(Point2d newPosition){
        this.relocate(newPosition.x,newPosition.y);
    }
    
    public void relocate(double x, double y){
        node.relocate(x, y);
    }

    @Override
    public Point2d getPos() {
        return new Point2d(this.position);
    }
}
