/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
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
    
    private Set<Updateable> toUpdate = Sets.newHashSet();
    
    
    private GameController() {
    }
    
    public void age(long seconds){
        for(Active a:  data.getActive()){
            a.age(seconds);
        }
        update();
       
    }
    
    public void addActive(Active a){
        data.addActive(a);
        update();
    }
    
    public void addDrawable(Drawable d){
        data.addDrawable(d);
        update();
    }
    
    public Collection<Drawable> getDrawable(){
        return data.getDrawable();
    }
    
    
    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }

    public Rect getWorldBounds() {
        return data.getStars().getBounds();
    }
    

    public void startUpdating(Updateable u){
       toUpdate.add(u);
    }
    
    public void stopUpdating(Updateable u){
        toUpdate.remove(u);
    }
    
    ImmutableSet<Drawable> watch(Rect viewingRect) {
        return data.getStars().getWithinBounds(viewingRect);
    }
    
    private static class GameControllerHolder {

        private static final GameController INSTANCE = new GameController();


    }
    
    private void setSelected(Set<Drawable> newSelection){
        selected.clear();
        selected.addAll(newSelection);
    }
    
    public void update(){
        for(Updateable u: toUpdate){
            u.update();
        }
    }
    
    private ObservableSet<Drawable> getSelected(){
        return selected;
    }
}
