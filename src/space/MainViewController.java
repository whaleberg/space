package space;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import vector.Point2d;


public class MainViewController
    implements Initializable {
    
    private final GameData data = GameData.getInstance();
    private final GameController controller = GameController.getInstance();
    
    
    @FXML //  fx:id="list"
    private ListView<Drawable> list; // Value injected by FXMLLoader
  
    @FXML //  fx:id="pane"
    private AnchorPane mapPane; // Value injected by FXMLLoader

    @FXML
    private Button button;
    
    @FXML
    private AnchorPane selectionPane;
    
    private ViewPane mapViewer;
    private ViewPane selectionViewer;

     
    public void handleButton(ActionEvent event){
        controller.age(1);
           
    }
    
    
 

    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'MainView.fxml'.";
        assert mapPane != null : "fx:id=\"pane\" was not injected: check your FXML file 'MainView.fxml'.";
        
        // initialize your logic here: all @FXML variables will have been injecte
    
       list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       
       final ObservableSet<Drawable> selectSet = FXCollections.observableSet();
       list.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Drawable>(){

            @Override
            public void onChanged(Change<? extends Drawable> change) {
                selectSet.clear();
                selectSet.addAll(list.getSelectionModel().getSelectedItems());
            }
       
       });
       controller.setSelected(selectSet);
   
       
       
             
 
       list.setCellFactory(new Callback<ListView<Drawable>, ListCell<Drawable>>() {

            @Override
            public ListCell<Drawable> call(ListView<Drawable> p) {
               return new PositionCell();
            }
        });
       
      
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
      controller.addActive(s5);
      controller.addActive(p2);
      controller.addActive(p);
      // addDrawable(p2);
       
       
       addViewer();
       list.getItems().addAll(data.getDrawable());
       Updateable listForcer = new Updateable() {

            @Override
            public void update() {
               list.getItems().setAll(data.getDrawable());
            }
        };
       controller.startUpdating(listForcer);
        
    }

    
    
    public void addViewer(){
        View overallView = new View(controller.getWorldBounds(), 
                        new Rect(new Point2d(),mapPane.getPrefHeight(), mapPane.getPrefWidth()));
                                

        mapViewer = new ViewPane(overallView);
        View selectionView = new View(controller.getWorldBounds(), 
                        new Rect(new Point2d(),mapPane.getPrefHeight(), mapPane.getPrefWidth()));
         

        selectionViewer = new ViewPane(selectionView);
        
        
        installViewer(mapPane, mapViewer);
        installViewer(selectionPane, selectionViewer);
        
        controller.selectedProperty().addListener(new SetChangeListener<Drawable>(){

            @Override
            public void onChanged(SetChangeListener.Change<? extends Drawable> change) {
               
                View newSelectionView = new View(controller.getGroupBounds(change.getSet()),
                                                    new Rect(new Point2d(), 
                                                             selectionViewer.getHeight(), 
                                                             selectionViewer.getWidth()));
                selectionViewer.setView(newSelectionView);
            }
            
        });
       
        
        
        controller.startUpdating(selectionViewer);
        controller.startUpdating(mapViewer);
    }

    private void installViewer(AnchorPane parent, ViewPane viewPane) {
        viewPane.setVisible(true);
        parent.getChildren().add(viewPane);
        AnchorPane.setTopAnchor(viewPane, 1.0);
        AnchorPane.setBottomAnchor(viewPane,1.0);
        AnchorPane.setLeftAnchor(viewPane, 1.0);
        AnchorPane.setRightAnchor(viewPane, 1.0);
        viewPane.relocate(0, 0);
        
    }
    
    private static class PositionCell extends ListCell<Drawable> {
       
        
        @Override
        public void updateItem(final Drawable item, boolean empty){
            super.updateItem(item, empty);
            final Label lbl = new Label();
            double x = 0;
            double y = 0;
            if(item != null){
                lbl.textProperty().set(item.getID()+" "+item.getPos().toString());
                item.posProperty().addListener(new ChangeListener<Point2d>(){

                    @Override
                    public void changed(ObservableValue<? extends Point2d> ov,
                                        Point2d oldValue, Point2d newValue) {
                       
                        lbl.textProperty().set(item.getID()+" "+item.getPos().toString());
                    }
                    
                });
                
               
                setGraphic(lbl);
            }
        }
       
    }

        
    

}
