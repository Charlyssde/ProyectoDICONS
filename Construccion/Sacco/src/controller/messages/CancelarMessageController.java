/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.messages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Software;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class CancelarMessageController implements Initializable {

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private AnchorPane anchorPane;
    
    private boolean decision;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eliminarElemento(MouseEvent event) {
      decision = true;
      
    }

    @FXML
    private void cancelarEliminacion(MouseEvent event) {
      decision = false;
      Stage stage = (Stage) anchorPane.getScene().getWindow(); 
      stage.close(); 
    }
    
    public boolean saberDecision(){
        return decision;
    }

  public void cargarSoftware(Software software) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


    
}
