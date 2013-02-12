/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

import space.geometry.Rect;
import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.property.ReadOnlySetWrapper;
import javafx.collections.ObservableSet;

/**
 *
 * @author sailfish
 */
public class GameController {
    private static final GameData data = GameData.getInstance();
    
    private ReadOnlySetWrapper<Drawable> selected = new ReadOnlySetWrapper<>();
    public void setSelected(ObservableSet<Drawable> selection){
        selected.set(selection);
    }
    public ObservableSet<Drawable> getSelected(){
        return selected.get();
    }
    public ReadOnlySetProperty<Drawable> selectedProperty(){
        return selected.getReadOnlyProperty();
    }
    
    
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
    
    public Rect getGroupBounds(Set<? extends Drawable> drawables){
        return StarMap.boundingRect(drawables);
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
        
    public void update(){
        for(Updateable u: toUpdate){
            u.update();
        }
    }
    

}
