/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;


import com.google.common.collect.ImmutableMap;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import space.mission.Mission;
import vector.Point2d;
import vector.Vector2d;
/**
 *
 * @author sailfish
 */
public class Ship implements Active, Drawable{
	public static double MAXSPEED = 10;
	
	
    private final StringProperty name;
    public String getName(){
    	return name.getValue();
    }
    public void setName(String newName){
    	this.name.set(newName);
    }
    public StringProperty nameProperty(){
    	return name;
    	
    }
	
    private ReadOnlyListWrapper<Mission> missions = new ReadOnlyListWrapper<>();
    private void setMissions(ObservableList<Mission> missionList){
        missions.set(missionList);
    }
    public ReadOnlyListProperty<Mission> missionsProperty(){
        return missions.getReadOnlyProperty();
    }
    public ObservableList<Mission> getMissions(){
        return missions.get();
    }
    
    private ReadOnlyObjectWrapper<Vector2d> velocity = new ReadOnlyObjectWrapper<>();
    public Vector2d getVelocity(){
        return velocity.get();
    }
    private void setVelocity(Vector2d veloc){
        velocity.set(veloc);
    }
    public ReadOnlyObjectProperty<Vector2d> velocityProperty(){
        return velocity.getReadOnlyProperty();
    }
    
    private ReadOnlyObjectWrapper<Point2d> position = new ReadOnlyObjectWrapper<>();
    public final Point2d getPosition() {
        return position.get();
    }
    private void setPosition(Point2d point) {
        position.set(point);
    }
    @Override
    public ReadOnlyObjectProperty<Point2d> posProperty() {
        return position.getReadOnlyProperty();
    }
    
    public void setDesiredSpeed(double speed){
    	speed = Math.max(0, speed);
    	speed = Math.min(speed, MAXSPEED);
    	setVelocity(getVelocity().normalize().scale(speed) );
    }
    
    private final String ID;
    
    public static final IconType icon = IconType.SHIP;
    
    
    
    public Ship(Point2d position, Vector2d velocity){

        setPosition(position);
        setVelocity(velocity);
        this.relocate(getPosition());
        this.ID = "Ship:"+StaticUtility.createID();
        this.name =  new SimpleStringProperty(ID);
    }
    

    
    public String getPositionAsAString(){
        return getPosition().getX() +"/" + getPosition().getY();
    }
    
    
    @Override
    public void age(long seconds){
        this.travel(seconds);
    }
    
    private void travel(long seconds){
       Point2d newPos = getPosition().translate(getVelocity().scale(seconds));
      
       relocate(newPos);
    }
    
    public final void relocate(Point2d newPosition){
        setPosition(newPosition);
    }

    @Override
    public Point2d getPos() {
        return getPosition();
    }
    @Override
    public String toString(){
        return this.getPositionAsAString();
    }
    
    

       @Override
    public String getID(){
        return ID;
    }


    @Override
    public IconType getIconType() {
        return icon;
    }

    @Override
    public ImmutableMap<String, Double> getDrawingParameters() {
       return ImmutableMap.of();
    }
	@Override
	public Point2d getPositionIn(long seconds) {
		return this.getPos().translate(getVelocity().scale(seconds));
	}

 
    
}
