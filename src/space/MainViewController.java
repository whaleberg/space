/**
 * Sample Skeleton for "MainView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package space;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import vector.Point2d;
import vector.Vector2d;


public class MainViewController
    implements Initializable {
    
    private final GameData data = GameData.getInstance();
    private final GameController controller = GameController.getInstance();
    
    
    @FXML //  fx:id="list"
    private ListView<Ship> list; // Value injected by FXMLLoader
  
    @FXML //  fx:id="pane"
    private Pane pane; // Value injected by FXMLLoader

    @FXML
    private Button button;
    private ViewPane viewer;

   // private final ObservableList<Active> actives = FXCollections.observableArrayList();
    
    public void handleButton(ActionEvent event){
        GameController.getInstance().age(1);
        viewer.update();
    }
    
    
   // Handler for Pane[fx:id="pane"] onMouseClicked
    public void handleMouseClick(MouseEvent event) {
        System.out.println(event.getSceneX()+"/"+event.getSceneY());
        Ship shp =  new Ship( new Point2d(event.getSceneX(), event.getSceneY()), 
                    new Vector2d(2,2));
        list.getItems().add(shp);
        GameController.getInstance().addActive(shp);
        controller.addDrawable(shp);
       
    }
    
//    private void addActive(Active a){
//        actives.add(a);
//        addDrawable(a);
//    }
    
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
                        viewer.getNode(s).setScaleX(1.0);
                        viewer.getNode(s).setScaleY(1.0);
                    }
                    for(Ship s: selected){
                        viewer.getNode(s).setScaleX(2.0);
                        viewer.getNode(s).setScaleY(2.0);
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
       //Star s2 = new Star( new Point2d(300, 200), 40, 70);
       controller.addActive(s1);
       //addActive( s2);
       
       Planet p = Planet.newSatelliteOf(s1, 70, 100, .1);
      // Planet p2 = Planet.newSatelliteOf(s1, 100, 30, 1);
      controller.addActive(p);
      // addDrawable(p2);
       
       Planet p3 = Planet.newSatelliteOf(p, 10, 20, 0);
       controller.addDrawable(p3);
       
       Random rand = new Random();
       for(int i = 0; i < 2; i ++){
           Planet tmp = Planet.newSatelliteOf(s1, rand.nextDouble()*100+52, 
                   rand.nextDouble()*100+10, rand.nextDouble()*2*Math.PI);
           controller.addActive(tmp);
       }
        
       
       addViewer();
       viewer.update();
    }

    public void addViewer(){
         View overallView = new View(controller.getWorldBounds(), 
                        new Rect(new Point2d(),pane.getPrefHeight(), pane.getPrefWidth()),
                                controller.getMap());

        

       viewer = new ViewPane(overallView);
       viewer.setVisible(true);
       pane.getChildren().add(viewer);
    
       viewer.relocate(0, 0);
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
