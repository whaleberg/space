/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;


import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import vector.Point2d;


/**
 *
 * @author sailfish
 */
public class StarMap {
    
   private List<Drawable> map;
  // private Multimap<Rect , ObservableSet<Drawable>> watchers;

    public List<Drawable> getMap() {
        return map;
    }
   
   public StarMap(){
       map = new ArrayList<>();
   }
   
   public void add(Drawable p){
       map.add(p);
   }
   
   public void remove(Drawable p){
       map.remove(p);
   }
   
   public ObservableSet<Drawable> watch(Rect r){
       return getWithinBounds(r);
   }
      
   private ObservableSet<Drawable> getWithinBounds(Rect r){
      ObservableSet<Drawable> contained = FXCollections.observableSet();
      for (Drawable p : map){
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
           
           l = Math.min(l, pos.getX());
           r = Math.max(r,pos.getX());
           t = Math.min(t, pos.getY());
           b = Math.max(b, pos.getY());
       }
       Point2d lt = new Point2d(l, t);
       Point2d rb = new Point2d(r, b);
       
       return new Rect(lt, rb);
   }

  
}
