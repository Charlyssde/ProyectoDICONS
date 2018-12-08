/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.ResponsableDAO;
import model.Responsable;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarResponsableController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TextField txtNumPersonal;
  @FXML
  private TextField txtNombre;
  @FXML
  private TextField txtTelefono;
  @FXML
  private TextField txtExtension;
  @FXML
  private TextField txtCorreo;
  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;

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
  private void guardarResponsable(MouseEvent event) throws SQLException {
    if(camposVacios()){
      mensaje("No pueden quedar campos vacios");
    }else{
      Responsable nuevo = new Responsable(txtNumPersonal.getText(), txtNombre.getText(), 
          txtTelefono.getText(),txtExtension.getText(), txtCorreo.getText());
      ResponsableDAO.agregarResponsable(nuevo);
      mensaje("Responsable guardado exitosamente");
    }
  }

  private boolean camposVacios(){
    if(txtNumPersonal.getText().isEmpty() || txtNombre.getText().isEmpty() ||
       txtTelefono.getText().isEmpty()|| txtCorreo.getText().isEmpty() || 
        txtExtension.getText().isEmpty()){
      return true;
    }
    return false;
  }
  
  
  
  
  
  
      private void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }
}
