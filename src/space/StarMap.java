/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import space.geometry.Rect;
import vector.Point2d;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;



/**
 *
 * @author sailfish
 */
public class StarMap {
    
   private List<Drawable> map;
  // private Multimap<Rect , ObservableSet<Drawable>> watchers;

    public ImmutableList<Drawable> getMap() {
        return ImmutableList.copyOf(map);
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
   
      
     
   public ImmutableSet<Drawable> getWithinBounds(Rect r){
      ImmutableSet.Builder<Drawable> contained = ImmutableSet.builder();
      for (Drawable p : map){
          if(r.contains(p.getPos())){
              contained.add(p);
          }
      }
      return contained.build();
   }
   
    public Rect getBounds(){
        return boundingRect(this.getMap());
    }
   
    public static Rect boundingRect(Collection<? extends Positionable> items) {
        if (items.isEmpty()){
            return new Rect(new Point2d(), 0,0);
        }
        double l = Double.MAX_VALUE;
        double r = 0;
        double t = Double.MAX_VALUE;
        double b = 0;
        for (Positionable p : items) {
            Point2d pos = p.getPos();
            l = Math.min(l, pos.getX());
            r = Math.max(r, pos.getX());
            t = Math.min(t, pos.getY());
            b = Math.max(b, pos.getY());
        }
        Point2d lt = new Point2d(l, t);
        Point2d rb = new Point2d(r, b);
        return new Rect(lt, rb);
    }


  
}
