/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.ICapteur;
import javafx.fxml.Initializable;

/**
 *
 * @author BASTIEN
 */
public abstract class AbstractController implements Initializable{
    
    
    public abstract void update();
}
