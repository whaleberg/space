/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space.ui;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import space.Drawable;
import space.GameController;
import space.Ship;
import space.Updateable;
import space.View;
import space.geometry.Rect;
import vector.Point2d;
import vector.Vector2d;

/**
 *
 * @author sailfish
 */
public class ViewPane extends Pane implements Updateable {
    final Circle tl = new Circle(0,0,50);
    final Circle tr = new Circle(this.getWidth()-1,0,50);
    final  Circle bl = new Circle(0, this.getHeight()-10, 50);
    final Circle br = new Circle(this.getWidth()-10, this.getHeight()-10,50);
    final Circle mid = new Circle(this.getWidth()/2, this.getHeight()/2, 10);
    private View view;
    final Map<String, Node> drawableMap = new HashMap<>();
    final Label lbl;
    final Set<Node> overlay = new HashSet<>();
    private static final Logger LOG = Logger.getLogger(ViewPane.class.getName());
   
    //private ImmutableSet<ViewedDrawable> watched;
    
    public ViewPane(View view) {
        super();
        setView(view);
        
        lbl = new Label(this.view.toString());
        overlay.add(lbl);
      
       
        overlay.add(tl);
        overlay.add(tr);
        overlay.add(br);
        overlay.add(bl);
      
        overlay.add(mid);
        
        this.getChildren().add(lbl);
        lbl.relocate(10, 10);
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if (t != t1) {
                    ViewPane.this.view.updateOutputRect(new Rect(new Point2d(),
                            ViewPane.this.getHeight(),
                            ViewPane.this.getWidth()));
                            ViewPane.this.update();
                            tr.setCenterX(t1.doubleValue());
                            br.setCenterX(t1.doubleValue());
                            mid.setCenterX(t1.doubleValue()/2);
                            lbl.setText(ViewPane.this.view.toString());
                            //System.out.println("Width"+t1);
                            
                }
            }
        });
        
        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if (t != t1) {
                    ViewPane.this.view.updateOutputRect(new Rect(new Point2d(),
                            ViewPane.this.getHeight(),
                            ViewPane.this.getWidth()));
                            ViewPane.this.update();
                            bl.setCenterY(t1.doubleValue());
                            br.setCenterY(t1.doubleValue());
                            mid.setCenterY(t1.doubleValue()/2);
                            lbl.setText(ViewPane.this.view.toString());
                            //System.out.println("Height:"+t1);

                }
            }
        });
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2d click = new Point2d(e.getX(), e.getY());
                LOG.log(Level.INFO, "View clicked:" + click + "->"
                        + ViewPane.this.view.project(click));

                Ship shp = new Ship(ViewPane.this.view.project(click), new Vector2d(2, 2));
                GameController.getInstance().addActive(shp);


            }
        });
        
        final ReadOnlySetProperty<Drawable> selected = GameController.getInstance().selectedProperty();
        selected.addListener(new SetChangeListener<Drawable>(){

            @Override
            public void onChanged(Change<? extends Drawable> change) {
                for(Node n : drawableMap.values()){
                    n.setScaleX(1.0);
                    n.setScaleY(1.0);
                }
                for(Drawable d: selected.get()){
                    Node n =lookUpNode(d);
                    if(n != null){
                        n.setScaleX(2.0);
                        n.setScaleY(2.0);
                    }
                }
            }
        });
    }

    public void setView(View view) {
        this.view = view;
        update();
    }

    private Node getNode(Drawable d) {
        Node n;
        if (drawableMap.containsKey(d.getID())) {
            n = drawableMap.get(d.getID());
            updateNode(n, d);
            return n;
        } else {
            n = generateNode(d);
            drawableMap.put(d.getID(), n);
            updateNode(n, d);
            return n;
        }
    }
    
    public Node lookUpNode(Drawable d){
        if(drawableMap.containsKey(d.getID())){
            return drawableMap.get(d.getID());
        } else {
            return null;
        }
    }
    
    private void updateNode(Node n, Drawable d) {
        Bounds bound = n.getLayoutBounds();
        double h = bound.getHeight();
        double w = bound.getWidth();
        n.relocate(d.getPos().getX() - w / 2, d.getPos().getY() - h / 2);
    }

    

    private Node generateNode(Drawable d) {
        final double DEFAULT_RADIUS = 10;
        final double MIN_RADIUS = 3;
        Node n;
        double rad = d.getDrawingParameters().containsKey("RADIUS") ? d.getDrawingParameters().get("RADIUS") : DEFAULT_RADIUS;
        rad = Math.max(rad, MIN_RADIUS);
        switch (d.getIconType()) {
            case SHIP:
                Rectangle rect = new Rectangle(10, 10, Color.RED);
                rect.relocate(d.getPos().getX(), d.getPos().getY());
                n = rect;
                break;
            case STAR:
                Circle circ = new Circle(d.getPos().getX(), d.getPos().getY(), rad, Color.YELLOW);
                n = circ;
                break;
            case PLANET:
                circ = new Circle(d.getPos().getX(), d.getPos().getY(), rad, Color.BLUE);
                n = circ;
                break;
            default:
                n = new Circle(d.getPos().getX(), d.getPos().getY(), rad, Color.RED);
        }
        return n;
    }

    @Override
    public void update() {
        ObservableList<Node> nodes = FXCollections.observableArrayList();
        for (Drawable d : view.getViewed()) {
            nodes.add(getNode(d));
        }
   
        this.getChildren().setAll(nodes);
        //this.getChildren().addAll(overlay);
        //System.out.println(this.getChildren().toString());
    }
   
    
}
