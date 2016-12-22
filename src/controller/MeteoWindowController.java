/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import Metier.ICapteur;
/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class MeteoWindowController extends AbstractController{

     
    @FXML private Spinner<Double> therm;
    private final ICapteur c;
    private DoubleProperty t;
    
    public MeteoWindowController(ICapteur c) throws Exception{
        this.c=c;
        t = new SimpleDoubleProperty(c.getTemperature());
        this.c.setObs(this);
        Runnable cp = (Runnable) this.c;
        new Thread(cp).start();
        
        
    }
    
    //@FXML
    //private Image img;
    @Override
    public void update(){
        System.out.println("ca marche pas a partir d'ici !!!!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        therm.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-10,40));
        therm.getValueFactory().valueProperty().bind(t.asObject());
    }
}