/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Capteur;
import Metier.ChoixImg;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import Metier.ICapteur;
import static Metier.ICapteur.cm;
import Metier.MegaCapteur;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class MeteoWindowController extends AbstractController{

    @FXML private ProgressBar thermometre;
    @FXML private Spinner<Double> therm ;
    @FXML private ImageView img;
    private final ICapteur c;
    private DoubleProperty t;

    
    public MeteoWindowController(ICapteur c){        
        this.c=c;
        t = new SimpleDoubleProperty(c.getTemperature()); 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.c.setObs(this);
        Image i = new Image(ChoixImg.chooser(c.getTemperature()));
        img.setImage(i);
        double d = ((c.getTemperature()+10)/100)*2;
        this.thermometre.setProgress(d);
        this.therm.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-10,40));
        this.therm.getValueFactory().valueProperty().bindBidirectional(t.asObject());
        this.therm.setEditable(false);
        this.update();
        this.c.activer();
    }
    
    
    @Override
    public void update(){
          double d = ((c.getTemperature()+10)/100)*2;
          this.thermometre.setProgress(d);
          double TempSpin = this.therm.getValue();
          double TempCap= this.c.getTemperature();
          double ecart= TempCap - TempSpin;
          this.therm.increment((int)ecart); 
          Image i = new Image(ChoixImg.chooser(c.getTemperature()));
          img.setImage(i);
    }
    
     public void stop(){
         this.c.arreter();
     }

    
}