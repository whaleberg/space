/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;


import com.sun.javafx.geom.RectBounds;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;
import javax.vecmath.Point2d;

/**
 *
 * @author sailfish
 */
public class StarMap {
    
   private List<Positionable> map;
   
   public StarMap(){
       map = new ArrayList<>();
   }
   
   public void add(Positionable p){
       map.add(p);
   }
   
   public void remove(Positionable p){
       map.remove(p);
   }
   
   public List<Positionable> getWithinBounds(Rect r){
      List<Positionable> contained = new ArrayList<>();
      for (Positionable p : map){
          if(r.contains(p.getPos())){
              contained.add(p);
          }
      }
      return contained;
   }
   
   public Rect getBounds(){
       double l= Double.MAX_VALUE;
       double r= 0;
       double t= Double.MAX_VALUE;  
       double b= 0;
       
       for(Positionable p : map){
           Point2d pos = p.getPos();
           l = Math.min(l, pos.x);
           r = Math.max(r,pos.x);
           t = Math.min(t, pos.y);
           b = Math.max(b, pos.y);
       }
       Point2d lt = new Point2d(l, t);
       Point2d rb = new Point2d(r, b);
       
       return new Rect(lt, rb);
   }

}
