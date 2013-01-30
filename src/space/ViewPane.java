/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.IdentityHashMap;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class ViewPane extends Pane {
    
    private View view;
    final Map<Drawable, Node> drawableMap = new IdentityHashMap<>();
    final Label lbl;
    
    public ViewPane(View view) {
        super();
        this.view = view;
        
                lbl = new Label(view.toString());
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
                            lbl.setText(ViewPane.this.view.toString());

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
                            lbl.setText(ViewPane.this.view.toString());

                }
            }
        });
    }
    
    public void setView(View view){
        this.view = view;
    }

    public Node getNode(Drawable d) {
        Node n;
        if (drawableMap.containsKey(d)) {
            n = drawableMap.get(d);
            updateNode(n, d);
            return n;
        } else {
            n = generateNode(d);
            drawableMap.put(d, n);
            updateNode(n, d);
            return n;
        }
    }

    private void updateTreeOfOrbitables(Orbitable orb) {
        getNode(orb);
        for (Orbitable o : orb.getDirectOrbiting()) {
            updateTreeOfOrbitables(o);
        }
    }

    private void updateNode(Node n, Drawable d) {
        Bounds bound = n.getLayoutBounds();
        double h = bound.getHeight();
        double w = bound.getWidth();
        n.relocate(d.getPos().getX() - w / 2, d.getPos().getY() - h / 2);
    }

    private void addDrawable(Drawable d) {
        this.getChildren().add(getNode(d));
        //System.out.println(mainViewController.pane.getChildrenUnmodifiable().toString());
    }

    private Node generateNode(Drawable d) {
        final double DEFAULT_RADIUS = 10;
        final double MIN_RADIUS = 1;
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

    public void update() {
        ObservableList<Node> nodes = FXCollections.observableArrayList();
        for (Drawable d : GameData.getInstance().getDrawable()) {
            nodes.add(getNode(d));
        }
        for (Active a : GameData.getInstance().getActive()) {
            a.age(1);
            if (a instanceof Orbitable) {
                updateTreeOfOrbitables((Orbitable) a);
            } else {
                getNode(a);
            }
        }
        this.getChildren().setAll(nodes);
        this.getChildren().add(lbl);
    }
   
    
}
