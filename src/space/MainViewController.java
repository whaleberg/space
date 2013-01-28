/**
 * Sample Skeleton for "MainView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package space;

import java.net.URL;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
         a.age(10);
         updateNode(a);
     }
    }

    private Node generateNode(Drawable d){
        Node n;
         switch (d.getIconType()){
            case SHIP: Rectangle rect = new Rectangle(10,10, Color.RED);
                       rect.relocate(d.getPos().getX(), d.getPos().getY());
                       n = rect; 
                       break;
            case STAR:  Circle circ = new Circle(d.getPos().getX(),
                        d.getPos().getY(), 10, Color.YELLOW);
                        n = circ;
                        break;
            default: n = new Circle(d.getPos().getX(), d.getPos().getY(), 10,
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
            return n;
        }  
    }
    
    
   // Handler for Pane[fx:id="pane"] onMouseClicked
    public void handleMouseClick(MouseEvent event) {
        System.out.println(event.getSceneX()+"/"+event.getSceneY());
        Ship shp = new Ship( new Point2d(event.getSceneX(), event.getSceneY()), new Vector2d(2,2));
        list.getItems().add(shp);
        actives.add(shp);
        addNewDrawable(shp);
        System.out.println(pane.getChildrenUnmodifiable().toString());
    }

    
    private void addNewDrawable(Drawable d){
        pane.getChildren().add(getNode(d));
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
       
       addNewDrawable( new Star(new Point2d(100,100),20, 50));
       addNewDrawable( new Star (new Point2d(300,200),60, 70));
       
        
    }

    private void updateNode(Drawable d){
        if(drawableMap.containsKey(d)){
            updateNode(drawableMap.get(d),d);
        }
    }
    
    private void updateNode(Node n, Drawable d) {
        n.relocate(d.getPos().getX(), d.getPos().getY());
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
