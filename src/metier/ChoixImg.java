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
public class ChoixImg {
    
    public static String chooser(double temp){
        if(temp<0){
            return "@Images/froid.png";
        }
        else if(temp>20){
            return "@Images/soleil.gif";
        }
        else return "@Images/normal.jpg";       
    } 
}
