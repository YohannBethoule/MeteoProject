/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;

public abstract class ICapteur{
    ThreadCapteur t;
    private int poids=1;
    public void setPoid(int p){
        poids=p;
    }
    public int getPoid(){
        return poids;
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

