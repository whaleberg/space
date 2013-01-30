/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableMap;
import java.util.Map.Entry;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class ViewedDrawable implements Drawable{

    private final Drawable d;
    private final View v;
    private ImmutableMap<String, Double> scaledMap= null;
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
        System.out.println(d.getPos().toString()+"->"+v.view(d.getPos()));
        return v.view(d.getPos());
    }
    
    @Override
    public String getID(){
        return d.getID();
    }
}
