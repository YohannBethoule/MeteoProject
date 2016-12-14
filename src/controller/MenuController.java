/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import metier.Borne;
import metier.Capteur;
import metier.Fenetre;
import metier.GenerationTemperature;

/**
 * FXML Controller class
 *
 * @author bagandboeu
 */
public class MenuController implements Initializable {
    
    @FXML
    private Spinner<Integer> min,max,init,fenetre;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        min.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        min.setEditable(true);
        max.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        max.setEditable(true);
        init.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        init.setEditable(true);
        fenetre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        fenetre.setEditable(true);
    }    
    
    @FXML
    private void algo1() throws Exception{
        GenerationTemperature gt = new Borne();
        Capteur c= new Capteur(gt);
        MeteoWindowController mw = new MeteoWindowController(c);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader();
        f.setController(mw);
        Parent root = f.load(getClass().getResource("/meteoproject/MeteoWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void algo2(){
        GenerationTemperature gt = new Borne(min.getValue(),max.getValue());
        Capteur c= new Capteur(gt);
    }
    
    @FXML
    private void algo3(){
        GenerationTemperature gt = new Fenetre(init.getValue(),fenetre.getValue());
        Capteur c= new Capteur(gt);
    }
    
}
