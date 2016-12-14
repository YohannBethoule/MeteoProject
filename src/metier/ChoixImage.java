/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;



/**
 *
 * @author bagandboeu
 */
public class ChoixImage {
    private String chemin;
    
    public ChoixImage(Double temperature){
        if(temperature<0){
            chemin="@images/froid.png";
        }
        else if(temperature>20){
            chemin="@images/soleil.gif";
        }
        else chemin="@images/normal.jpg";
    }
    
    
    
}
