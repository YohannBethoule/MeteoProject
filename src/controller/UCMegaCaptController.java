/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class UCMegaCaptController extends HBox implements Initializable {

    
    public UCMegaCaptController(){
        try{
           FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/UCMegaCapt.fxml"));
           f.setController(this);
           f.setRoot(this);
           f.load();
        }catch(Exception e){
            System.out.println("Controller.UCCaptController.<init>()");
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
