/**
 * Sample Skeleton for "MainView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package space;

import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML
    private Button button;
    private ViewPane viewer;

   // private final ObservableList<Active> actives = FXCollections.observableArrayList();
    
    public void handleButton(ActionEvent event){
        GameController.getInstance().age(1);
        
            View overallView = new View(controller.getWorldBounds(), 
                        new Rect(new Point2d(),pane.getHeight(), pane.getWidth()),
                                controller.getMap());
            viewer.update();
      //  viewer.setView(overallView);
        
    }
    
    
   // Handler for Pane[fx:id="pane"] onMouseClicked
    public void handleMouseClick(MouseEvent event) {
        System.out.println(event.getSceneX()+"/"+event.getSceneY());
        Ship shp =  new Ship( new Point2d(event.getSceneX(), event.getSceneY()), 
                    new Vector2d(2,2));
        list.getItems().add(shp);
        GameController.getInstance().addActive(shp);
        controller.addDrawable(shp);
        viewer.update();
       
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
       
      
//       Star s1 = new Star( new Point2d(100, 100), 20, 50);
//       //Star s2 = new Star( new Point2d(300, 200), 40, 70);
//       controller.addActive(s1);
//       //addActive( s2);
//       

//       
//       Planet p3 = Planet.newSatelliteOf(p, 10, 20, 0);
//       controller.addDrawable(p3);
//       
//       Random rand = new Random();
//       for(int i = 0; i < 2; i ++){
//           Planet tmp = Planet.newSatelliteOf(s1, rand.nextDouble()*100+52, 
//                   rand.nextDouble()*100+10, rand.nextDouble()*2*Math.PI);
//           controller.addActive(tmp);
//       }
       Star s1, s2, s3, s4;
       s1 = new Star(new Point2d(-1000,-1000), 5, 5 );
       s2 = new Star(new Point2d(1000,-1000), 5, 5 ); 
       s3 = new Star(new Point2d(-1000,1000), 5, 5 );
       s4 = new Star(new Point2d(1000,1000), 5, 5 );
       controller.addActive(s1);
       controller.addActive(s2);
       controller.addActive(s3);
       controller.addActive(s4);
       
      Star s5 = new Star(new Point2d(), 5,50);
      Planet p = Planet.newSatelliteOf(s5, 50, 100, .1);
      Planet p2 = Planet.newSatelliteOf(s1, 100, 30, 1);
      controller.addActive(p);
      // addDrawable(p2);
       
       
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
       AnchorPane.setTopAnchor(viewer, 1.0);
       AnchorPane.setBottomAnchor(viewer,1.0);
       AnchorPane.setLeftAnchor(viewer, 1.0);
       AnchorPane.setRightAnchor(viewer, 1.0);
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
