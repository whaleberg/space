/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.runtime.Log;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class View {

    private Rect viewingRect;
    private Rect outputRect;
 
    final private GameController controller = GameController.getInstance();
    private double scale;
    private double translateX, translateY;
    private final Rect idealViewingRect;
    private static final Logger LOG = Logger.getLogger(View.class.getName());
    
  
    public View(Rect viewingRect, Rect outputRect) {
        assert (viewingRect != null);
        assert (outputRect != null);

        this.idealViewingRect = viewingRect;
        this.viewingRect = viewingRect.expandToAspectRatio(outputRect);
        this.outputRect = outputRect;
        setScale();
        
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
    }

    public Point2d view(Point2d p) {
        return new Point2d(view(p.getX() + translateX), view(p.getY() + translateY));
    }

    public Point2d project(Point2d p) {
        return new Point2d(project(p.getX()) - translateX, project(p.getY()) - translateY);
    }

    public ImmutableSet<ViewedDrawable> getViewed() {
        ImmutableSet<Drawable> draw = controller.watch(viewingRect);
        ImmutableSet.Builder<ViewedDrawable> viewed = ImmutableSet.builder();
        for (Drawable d: draw){
            viewed.add(ViewedDrawable.wrap(d, this));
        }
        LOG.log(Level.INFO, viewed.build().toString());
        return viewed.build();
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

  
}
