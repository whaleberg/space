/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.Sets;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author sailfish
 */
public class GameController {
    private static final GameData data = GameData.getInstance();
    private ObservableSet<Drawable> selected = FXCollections.observableSet();
    
    
    private GameController() {
    }
    
    public void age(long seconds){
        for(Active a:  data.getActive()){
            a.age(seconds);
        }
       
    }
    
    public void addActive(Active a){
        data.addActive(a);
    }
    
    public void addDrawable(Drawable d){
        data.addDrawable(d);
    }
    
    public void addView(View v){
       assert false;
    }
    
    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }

    public Rect getWorldBounds() {
        return data.getStars().getBounds();
    }
    
    public StarMap getMap(){
        return data.getStars();
    }
    
    private static class GameControllerHolder {

        private static final GameController INSTANCE = new GameController();
    }
    
    private void setSelected(Set<Drawable> newSelection){
        selected.clear();
        selected.addAll(newSelection);
    }
    
    private ObservableSet<Drawable> getSelected(){
        return selected;
    }
}
