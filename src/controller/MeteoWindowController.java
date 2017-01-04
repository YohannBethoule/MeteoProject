/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.ChoixImg;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import Metier.ICapteur;
import com.sun.scenario.effect.ImageData;
import java.awt.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class MeteoWindowController extends AbstractController{

    @FXML private ProgressBar thermometre;
    @FXML private Spinner<Double> therm ;
    private final ICapteur c;
    private DoubleProperty t;
    
    public MeteoWindowController(ICapteur c) throws Exception{        
        this.c=c;
        thermometre=new ProgressBar();
        t = new SimpleDoubleProperty(c.getTemperature());
        c.setObs(this);
        therm = new Spinner<Double>();
        double d = ((c.getTemperature()+10)/100)*2;
        this.thermometre.setProgress(d);
        Runnable cp = (Runnable) this.c;
        new Thread(cp).start();
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.therm.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-10,40));
        this.therm.getValueFactory().valueProperty().bindBidirectional(t.asObject());
        this.therm.setEditable(false);
        this.update();
    }
    
    
    @Override
    public void update(){
        try{
          double d = ((c.getTemperature()+10)/100)*2;
          this.thermometre.setProgress(d);
          double TempSpin = this.therm.getValue();
          double TempCap= this.c.getTemperature();
          double ecart= TempCap - TempSpin;
          this.therm.increment((int)ecart); 
          
        }catch(Exception e){
            System.out.println("Controller.MeteoWindowController.update()");
        }
    }
    
    @FXML
     public void handle(ActionEvent e){
         this.c.arreter();
     }
    
}