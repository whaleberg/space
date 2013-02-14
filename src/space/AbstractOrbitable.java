/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import vector.Point2d;
import vector.Vector2d;

/**
 *
 * @author sailfish
 */
public abstract class AbstractOrbitable implements Orbitable {
    private final PhysicalBody body = new PhysicalBody();
    private Orbitable parent = null;
    
  
    
    private final String ID;
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
    
    public AbstractOrbitable(){
    	ID = StaticUtility.createID();
    	name = new SimpleStringProperty(ID);
    }
    
    @Override
    public String getID(){
        return ID;
    }
    
    public PhysicalBody getBody() {
        return body;
    }
    
    private final List<Orbitable> children = new ArrayList<>();

    @Override
    public boolean addOrbiter(Orbitable orb) {
        orb.setParent(this);
        return children.add(orb);
    }

    @Override
    public void age(long seconds) {
        for (Orbitable orb : children) {
            orb.age(seconds);
        }
    }

    @Override
    public Collection<Orbitable> getDirectOrbiting() {
        return Collections.unmodifiableCollection(children);
    }

    @Override
    public double getMass() {
        return body.getMass();
    }

    @Override
    public Point2d getPos() {
        return body.getPos();
    }

    @Override
    public double getRadius() {
        return body.getRadius();
    }

    @Override
    public boolean removeOrbiter(Orbitable orb) {
        return children.remove(orb);
    }

    @Override
    public void setParent(Orbitable orb){
        this.parent = orb;
    }
    
    public Orbitable getParent(){
        return parent;
    }
    
    @Override
    public ReadOnlyObjectProperty<Point2d> posProperty(){
        return body.positionProperty();
    }
    
    @Override
    public ImmutableMap<String, Double> getDrawingParameters(){
        ImmutableMap.Builder<String,Double> bld = ImmutableMap.builder();
        bld.put("RADIUS", this.getRadius());
        return bld.build();
    }
    
	@Override
	/**
	 * Mobile subclasses must override this and call super before moving.
	 */
	public Point2d getPositionIn(long seconds) {
		Orbitable parent = getParent();
		if(  parent != null){
			Vector2d displacementFromParent = getPos().getVector().sub(parent.getPos().getVector() );
			Point2d futureParentPos = parent.getPositionIn(seconds);
			Point2d futurePos = futureParentPos.translate(displacementFromParent);
			return futurePos;
		} else {
			return getPos();
		}
	}
}
