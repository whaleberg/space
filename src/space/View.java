/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Map;
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
 
    final private ObservableSet<ViewedDrawable> scaled = FXCollections.observableSet();
    final private GameController controller = GameController.getInstance();
    private double scale;
    private double translateX, translateY;
    private Rect idealViewingRect;
    final private Map<Drawable, ViewedDrawable> scaledMap = Maps.newIdentityHashMap();

    public View(Rect viewingRect, Rect outputRect) {
        assert (viewingRect != null);
        assert (outputRect != null);

        this.idealViewingRect = viewingRect;
        this.viewingRect = viewingRect.expandToAspectRatio(outputRect);
        this.outputRect = outputRect;
        setScale();
        
    }

    private void removeFromScaled(Drawable d) {
        scaled.remove(scaledMap.get(d));
        scaledMap.remove(d);
    }

    private void addToScaled(Drawable d) {
        ViewedDrawable vd = new ViewedDrawable(d, this);
        scaled.add(vd);
        scaledMap.put(d, vd);
    }

    public double view(double length) {
        return length * scale;
    }

    public double project(double length) {
        return length / scale;
    }

    public void updateOutputRect(Rect r) {
        this.outputRect = r;
        viewingRect = idealViewingRect.expandToAspectRatio(outputRect);
        setScale();
        update();
    }

    public Point2d view(Point2d p) {
        return new Point2d(view(p.getX() + translateX), view(p.getY() + translateY));
    }

    public Point2d project(Point2d p) {
        return new Point2d(project(p.getX()) + translateX, project(p.getY()) + translateY);
    }

    public ObservableSet<? extends Drawable> getViewed() {
        update();
        return scaled;
    }

    @Override
    public String toString() {
        return "View{" + "viewingRect=" + viewingRect + ", outputRect=" + outputRect + '}';
    }

    private void setScale() {
        scale = outputRect.width / viewingRect.width;
        translateX = outputRect.topLeft.getX() - viewingRect.topLeft.getX();
        translateY = outputRect.topLeft.getY() - viewingRect.topLeft.getY();
    }

    void update() {
       ImmutableSet<Drawable> updated = controller.watch(viewingRect);
       for(Drawable d : updated){
           if(!scaledMap.containsKey(d)){
               addToScaled(d);
           }
       }
       for(Drawable d: scaledMap.keySet()){
           if(!updated.contains(d)){
               removeFromScaled(d);
           }
       }
    }
}
