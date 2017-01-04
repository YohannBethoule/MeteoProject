/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;

public abstract class ICapteur {

    
    public abstract double getTemperature();
    public AbstractController observer;
    public void setObs(AbstractController obs){
        this.observer=obs;
    }
    public long IntGeneration;
    public abstract void arreter();
    public abstract void run();
    public abstract void regenere();

}

