/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

/**
 *
 * @author sailfish
 */
public class GameController {
    
    private GameController() {
    }
    
    public void age(long seconds){
        for(Active a:  GameData.getInstance().getActive()){
            a.age(seconds);
        }
       
    }
    
    public void addActive(Active a){
        GameData.getInstance().addActive(a);
    }
    
    public void addDrawable(Drawable d){
        GameData.getInstance().addDrawable(d);
    }
    
    public static GameController getInstance() {
        return GameControllerHolder.INSTANCE;
    }
    
    private static class GameControllerHolder {

        private static final GameController INSTANCE = new GameController();
    }
}
