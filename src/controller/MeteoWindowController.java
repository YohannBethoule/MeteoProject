/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.Capteur;


/**
 *
 * @author bagandboeu
 */
public class MeteoWindowController implements Initializable {
    
    
    @FXML private Spinner<Double> therm;
    private final Capteur c;
    private final DoubleProperty t;
    
    public MeteoWindowController(Capteur c) throws Exception{
        this.c=c;
        t = new SimpleDoubleProperty(c.getTemperature());
        System.out.println(c.getTemperature());
    }
    
    //@FXML
    //private Image img;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        therm.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-10,40));
        therm.getValueFactory().valueProperty().bind(t.asObject());  
    }
    
    
}
