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
public class ThreadCapteur extends Thread{
    
    public volatile boolean actif=true;
    private final ICapteur c;
    
    public ThreadCapteur(ICapteur c){
        this.c=c;
    }
    
    public void go(){
        actif=true;
        this.start();
    }
    
    public void arreter(){
        this.actif=false;
    }
    
    @Override
    public void run(){
        while(actif){
            c.traitement();
            if(this.c.observer != null){
               this.c.observer.update();
            }
            try{
            if(this.c.IntGeneration!=0){
                sleep(this.c.IntGeneration);
            }else{
                sleep(10);
            }
            }catch(Exception e){
                System.out.println("Metier.ThreadCapteur.run()");
            }
        }
    }
    
}