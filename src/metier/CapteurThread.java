/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;


/**
 *
 * @author BASTIEN
 */
public abstract class CapteurThread implements Runnable{

    Thread t;
    long time;
    
    public CapteurThread(long time){
        t=new Thread();
        this.time=time;
    }
    
    /*public CapteurThread getCapteurThread(){
        return this;
    }
    */
    
    public void run(Capteur c){
        t.start();
        for(int i=0;i<1000;i++){

        }
    }

}
