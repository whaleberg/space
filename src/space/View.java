/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class View {
   private Rect viewingRect;
   private Rect outputRect;
   private final StarMap map;
   private final ObservableSet<Drawable> viewed;
   
   private double scaleX; // scaleY;
   private double translateX, translateY;
    private Rect idealViewingRect;
      
   public View(Rect viewingRect, Rect outputRect, StarMap map){
       assert (viewingRect != null);
       assert (outputRect != null);
       assert (map != null);
       
       this.idealViewingRect = viewingRect;
       this.viewingRect = viewingRect.expandToAspectRatio(outputRect);
       this.outputRect = outputRect;
       this.map = map;
       
       scaleX = this.outputRect.width / this.viewingRect.width;
      // scaleY = outputRect.height/ viewingRect.height;
       translateX = this.outputRect.topLeft.getX() - this.viewingRect.topLeft.getX(); 
       translateY = this.outputRect.topLeft.getY() - this.viewingRect.topLeft.getY();
       
       viewed = this.map.watch(viewingRect);
   }
    
   public double view(double length){
       return length * scaleX;
   }
   public double project(double length){
       return length / scaleX;
   }
   
   public void updateOutputRect(Rect r){
       this.outputRect = r;
       viewingRect = idealViewingRect.expandToAspectRatio(outputRect);
   }
   
   public Point2d view( Point2d p){
       return new Point2d( view(p.getX() + translateX), view(p.getY()-translateY) );
   }
   
   public Point2d project(Point2d p){
       return new Point2d( project(p.getX())-translateX, project(p.getY())+translateY);
   }
   
   public ObservableSet<Drawable> getViewed(){
       viewed.clear();
       viewed.addAll(map.watch(viewingRect));
       ObservableSet<Drawable> scaled = FXCollections.observableSet();
       for(Drawable d : viewed){
           scaled.add(new ViewedDrawable(d, this));
       }
       return scaled;
   }

    @Override
    public String toString() {
        return "View{" + "viewingRect=" + viewingRect + ", outputRect=" + outputRect + '}';
    }
   

   
}
