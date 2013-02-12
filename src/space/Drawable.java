/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.beans.property.StringProperty;

import com.google.common.collect.ImmutableMap;

/**
 *
 * @author sailfish
 */
public interface Drawable extends Positionable {
    
    public IconType getIconType();
    public ImmutableMap<String, Double> getDrawingParameters(); 
    public String getID();
    public String getName();
    public void setName(String newName);
    public StringProperty nameProperty();
    
}
