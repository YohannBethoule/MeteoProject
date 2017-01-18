/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public interface GenerationTemperature {
    
    public double randomPick();
    @Override
    public String toString();

    public StringProperty algoProperty();
    public String getAlgo();
    void setAlgo();
    
}