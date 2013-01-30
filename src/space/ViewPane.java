/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class ViewPane extends Pane {
    
    private View view;
    
    public ViewPane(View view) {
        super();
        this.view = view;
        Label lbl = new Label(view.toString());
        this.getChildren().add(lbl);
        lbl.relocate(10, 10);
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if (t != t1) {
                    ViewPane.this.view.updateOutputRect(new Rect(new Point2d(),
                            ViewPane.this.getHeight(),
                            ViewPane.this.getWidth()));

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

                }
            }
        });
    }
    
    public void setView(View view){
        this.view = view;
    }
   
    
}
