/**
 * Sample Skeleton for "MainView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package space.ui;

import java.net.URL;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import space.Active;
import space.Drawable;
import space.Orbitable;
import space.Planet;
import space.Ship;
import space.Star;
import vector.Point2d;
import vector.Vector2d;


public class MainViewController
    implements Initializable {

    @FXML //  fx:id="list"
    private ListView<Ship> list; // Value injected by FXMLLoader
  
    @FXML //  fx:id="pane"
    private Pane pane; // Value injected by FXMLLoader

    @FXML
    private Button button;
    
    private final Map<Drawable, Node> drawableMap= new IdentityHashMap<>();
    

    private final ObservableList<Active> actives = FXCollections.observableArrayList();
    
    public void handleButton(ActionEvent event){
     for (Active a : actives){
         a.age(1);
         if(a instanceof Orbitable){
             updateTreeOfOrbitables((Orbitable)a);
         } else {
             updateNode(a);
         }
     }
    }

    private void updateTreeOfOrbitables(Orbitable orb){
        updateNode(orb);
        for(Orbitable o : orb.getDirectOrbiting()){
            updateTreeOfOrbitables(o);
        }
    }
    
    private Node generateNode(Drawable d){
        final double DEFAULT_RADIUS = 10;
        final double MIN_RADIUS = 1;
        Node n;
        double rad = d.getDrawingParameters().containsKey("RADIUS") 
                     ? d.getDrawingParameters().get("RADIUS") : DEFAULT_RADIUS;
        rad = Math.max(rad, MIN_RADIUS);
         switch (d.getIconType()){
            case SHIP: Rectangle rect = new Rectangle(10,10, Color.RED);
                       rect.relocate(d.getPos().getX(), d.getPos().getY());
                       n = rect; 
                       break;
            case STAR:  Circle circ = new Circle(d.getPos().getX(),
                        d.getPos().getY(), rad, Color.YELLOW);
                        n = circ;
                        break;
            case PLANET: circ = new Circle(d.getPos().getX(),
                         d.getPos().getY(), rad, Color.BLUE);
                         n = circ;
                         break;
            default: n = new Circle(d.getPos().getX(), d.getPos().getY(), rad,
                       Color.RED);
          }
        return n;
    }
    
    
    
    private Node getNode(Drawable d){
        Node n;
        if (drawableMap.containsKey(d)){
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
    
    
   // Handler for Pane[fx:id="pane"] onMouseClicked
    public void handleMouseClick(MouseEvent event) {
        System.out.println(event.getSceneX()+"/"+event.getSceneY());
        Ship shp =  new Ship( new Point2d(event.getSceneX(), event.getSceneY()), 
                    new Vector2d(2,2));
        list.getItems().add(shp);
        addActive(shp);
        System.out.println(pane.getChildrenUnmodifiable().toString());
    }

    
    private void addDrawable(Drawable d){
        pane.getChildren().add(getNode(d));
    }
    
    private void addActive(Active a){
        actives.add(a);
        addDrawable(a);
    }
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'MainView.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'MainView.fxml'.";
        
        // initialize your logic here: all @FXML variables will have been injecte
        //list.setItems(pane.getChildren());
       list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       list.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Ship>() {

            @Override
            public void onChanged(Change<? extends Ship> c) {
                while (c.next()) {
                    ObservableList<? extends Ship> selected = list.getSelectionModel().getSelectedItems();
                    for(Ship s: list.getItems()){
                        getNode(s).setScaleX(1.0);
                        getNode(s).setScaleY(1.0);
                    }
                    for(Ship s: selected){
                        getNode(s).setScaleX(2.0);
                        getNode(s).setScaleY(2.0);
                    }
                }
            }
       });
       
       list.setCellFactory(new Callback<ListView<Ship>, ListCell<Ship>>() {

            @Override
            public ListCell<Ship> call(ListView<Ship> p) {
               return new PositionCell();
            }
        });
       
       Star s1 = new Star( new Point2d(100, 100), 20, 50);
       Star s2 = new Star( new Point2d(300, 200), 40, 70);
       addActive( s1);
       addActive( s2);
       
       Planet p = Planet.newSatelliteOf(s1, 70, 100, .1);
      // Planet p2 = Planet.newSatelliteOf(s1, 100, 30, 1);
       addDrawable(p);
      // addDrawable(p2);
       
       Planet p3 = Planet.newSatelliteOf(p, 10, 20, 0);
       addDrawable(p3);
       
       Random rand = new Random();
       for(int i = 0; i < 100; i ++){
           Planet tmp = Planet.newSatelliteOf(s1, rand.nextDouble()*100+52, 
                   rand.nextDouble()*100+10, rand.nextDouble()*2*Math.PI);
           addDrawable(tmp);
       }
       
    }

    private void updateNode(Drawable d){
        if(drawableMap.containsKey(d)){
            updateNode(drawableMap.get(d),d);
        }
    }
    
    private void updateNode(Node n, Drawable d) {
        Bounds bound = n.getLayoutBounds();
        double h = bound.getHeight();
        double w = bound.getWidth();
        n.relocate(d.getPos().getX()-w/2, d.getPos().getY() - h/2);
    }

    private static class PositionCell extends ListCell<Ship> {
        
        @Override
        public void updateItem(Ship item, boolean empty){
            super.updateItem(item, empty);
            Label lbl = new Label();
            if(item != null){
                lbl.textProperty().bind(item.positionStringProperty());
                setGraphic(lbl);
            }
        }
       
    }

        
    

}
