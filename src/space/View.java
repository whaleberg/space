/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class View {
   private Rect viewingRect;
   private Rect outputRect;
   
   private final ObservableSet<Drawable> viewed;
   
   private double scaleX; // scaleY;
   private double translateX, translateY;
      
   public View(Rect viewingRect, Rect outputRect, StarMap map){
       assert viewingRect.matchesAspectRatio(outputRect);
       scaleX = outputRect.width / viewingRect.width;
      // scaleY = outputRect.height/ viewingRect.height;
       translateX = outputRect.topLeft.getX() - viewingRect.topLeft.getX(); 
       translateY = outputRect.topLeft.getY() - viewingRect.topLeft.getY();
       viewed = map.watch(viewingRect);
   }
    
   public double view(double length){
       return length * scaleX;
   }
   public double project(double length){
       return length / scaleX;
   }
   
   public Point2d view( Point2d p){
       return new Point2d( view(p.getX() + translateX), view(p.getY()-translateY) );
   }
   
   public Point2d project(Point2d p){
       return new Point2d( project(p.getX())-translateX, project(p.getY())+translateY);
   }
   
   public ObservableSet<Drawable> getViewed(){
       ObservableSet<Drawable> scaled = FXCollections.observableSet();
       for(Drawable d : viewed){
           scaled.add(new ViewedDrawable(d, this));
       }
       return scaled;
   }
   
   
   
}
