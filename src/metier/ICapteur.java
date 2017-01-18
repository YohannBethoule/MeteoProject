/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ICapteur{
    ThreadCapteur t;
    
    private IntegerProperty poidP = new SimpleIntegerProperty();
    public IntegerProperty poidProperty(){
        return poidP;
    }
    public int getPoid(){
        return poidP.get();
    }
    public void setPoid(int p){
        this.poidP.set(p);
    }
    
    public static CapteurManager cm = new CapteurManager();
    public abstract double getTemperature();
    public AbstractController observer;
    public void setObs(AbstractController obs){
        this.observer=obs;
    }
    public long IntGeneration;
    public abstract void arreter();
    @Override
    public abstract String toString();
    public abstract void activer();
    public abstract void traitement();
}

