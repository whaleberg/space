/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sailfish
 */
public class GameData {
    private StarMap stars = new StarMap();
    private List<Active> activeRoots = new LinkedList<>();        
    
    private GameData() {
    }
    
    public static GameData getInstance() {
        return GameDataHolder.INSTANCE;
    }
    
    public void addDrawable(Drawable d){
        stars.add(d);
    }
    public void addActive(Active a){
        activeRoots.add(a);
        addDrawable(a);
    }
    
    public StarMap getStars(){
        return stars;
    }
    
    public ImmutableList<Drawable> getDrawable(){
        return ImmutableList.copyOf(stars.getMap());
    }
    
    public ImmutableList<Active> getActive(){
        return ImmutableList.copyOf(activeRoots);
    }
    
    private static class GameDataHolder {

        private static final GameData INSTANCE = new GameData();

    }
}
