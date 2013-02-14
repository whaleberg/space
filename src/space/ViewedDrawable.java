/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableMap;
import java.util.Map.Entry;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class ViewedDrawable implements Drawable{

    private final Drawable d;
    private final View v;
    private ImmutableMap<String, Double> scaledMap= null;
    
    public static ViewedDrawable wrap(Drawable d, View v){
        return new ViewedDrawable(d, v);
    }
    
    public ViewedDrawable(Drawable d, View v){
        this.d = d;
        this.v = v;
    }
    
    @Override
    public IconType getIconType() {
        return d.getIconType();
    }

    @Override
    public ImmutableMap<String, Double> getDrawingParameters() {
        if(scaledMap == null){
             ImmutableMap.Builder<String, Double> bld= ImmutableMap.builder();
             for(Entry<String,Double> e: d.getDrawingParameters().entrySet()){
                 bld.put(e.getKey(),v.view(e.getValue()));
             }
             scaledMap = bld.build();
        }
        return scaledMap;
    }

    @Override
    public Point2d getPos() {
        //System.out.println(d.getPos().toString()+"->"+v.view(d.getPos()));
        return v.view(d.getPos());
    }
    
    
    
    @Override
    public String getID(){
        return d.getID();
    }
    
    @Override
    public String toString(){
        return getID();
    }

    @Override
    public ReadOnlyObjectProperty<Point2d> posProperty() {
      
        
        final ReadOnlyObjectProperty<Point2d> pos = d.posProperty();
        final ReadOnlyObjectWrapper<Point2d> out = new ReadOnlyObjectWrapper<>();
        ObjectBinding<Point2d> transformed = new ObjectBinding<Point2d>(){
            {
                super.bind(pos);
            }
            
            @Override
            protected Point2d computeValue() {
                return v.view(pos.get());
            }
            
        };
        out.bind(transformed);
        return out.getReadOnlyProperty();
    }

	@Override
	public String getName() {
		return d.getName();
	}

	@Override
	public void setName(String newName) {
		d.setName(newName);
	}

	@Override
	public StringProperty nameProperty() {
		return d.nameProperty();
	}

	@Override
	public Point2d getPositionIn(long seconds) {
		return d.getPositionIn(seconds);
	}

 
}
