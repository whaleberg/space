/**
 * Sample Skeleton for "MainView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package space;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    

    private final ObservableList<Ship> ships = FXCollections.observableArrayList();
    public void handleButton(ActionEvent event){
     for (Ship s: list.getItems()){
         s.age(10);
     }
    }

   
     
   // Handler for Pane[fx:id="pane"] onMouseClicked
    public void handleMouseClick(MouseEvent event) {
        System.out.println(event.getSceneX()+"/"+event.getSceneY());
        Rectangle rect = new Rectangle(10,10, Color.RED);
        Ship shp = new Ship(rect, new Point2d(event.getSceneX(), event.getSceneY()), new Vector2d(2,2));
        list.getItems().add(shp);
        pane.getChildren().add(shp.getNode());
        System.out.println(pane.getChildrenUnmodifiable().toString());
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
                        s.getNode().setScaleX(1.0);
                        s.getNode().setScaleY(1.0);
                    }
                    for(Ship s: selected){
                        s.getNode().setScaleX(2.0);
                        s.getNode().setScaleY(2.0);
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
