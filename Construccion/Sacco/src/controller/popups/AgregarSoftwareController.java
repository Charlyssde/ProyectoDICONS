/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarSoftwareController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtSoftware;
    @FXML
    private TextArea txtObservaciones;
    @FXML
    private TextField txtVersion;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cancelarRegistro(MouseEvent event) {
      Stage stage = (Stage) anchorPane.getScene().getWindow(); 
      stage.close(); 
      
    }

    @FXML
    private void guardarSoftware(MouseEvent event) {
      try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/messages/ExitoMessage.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Éxito");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
      Stage thisStage = (Stage)anchorPane.getScene().getWindow();
      thisStage.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    }
    
}
