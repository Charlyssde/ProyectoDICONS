/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.HardwareDAO;
import model.DAO.TecnicoAcademicoDAO;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class EditarTecnicoController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnGuardar;
  @FXML
  private TextField txtNumPersonal;
  @FXML
  private Button btnCancelar;
  @FXML
  private TextField txtNombre;
  @FXML
  private TextField txtTelefono;
  @FXML
  private TextField txtExtension;
  @FXML
  private TextField txtCorreo;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private Label lblShowPass;
  @FXML
  private TextField txtPasswordShowing;
  private TecnicoAcademico tecnico;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void actualizarTecnico(MouseEvent event) throws SQLException {
    if(camposVacios()){
      mensaje("No se pueden quedar campos vacios");
    }else{
      TecnicoAcademico nuevo = new TecnicoAcademico(
          tecnico.getNumPersonal(), tecnico.getNombre(), txtPassword.getText(), 
          txtTelefono.getText(), txtExtension.getText(), tecnico.getCorreoInstitucional());
      TecnicoAcademicoDAO.editarTecnicoAcademico(nuevo);
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
      mensaje("Tecnico actualizado correctamente");
    }
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void habilitarShowPass(KeyEvent event) {
    lblShowPass.setVisible(true);
  }

  @FXML
  private void hidePassword(MouseEvent event) {
    txtPasswordShowing.setVisible(false);
    txtPassword.setVisible(true);
  }

  @FXML
  private void mostrarPassword(MouseEvent event) {
    txtPasswordShowing.setVisible(true);
    txtPassword.setVisible(false);
    txtPasswordShowing.setText(txtPassword.getText());
  }

  public void cargarTecnico(TecnicoAcademico nuevo){
    this.tecnico = nuevo;
    txtNumPersonal.setText(tecnico.getNumPersonal());
    txtNombre.setText(tecnico.getNombre());
    txtCorreo.setText(tecnico.getCorreoInstitucional());
    txtTelefono.setText(tecnico.getTelefono());
    txtExtension.setText(tecnico.getExtension());
    txtPassword.setText(tecnico.getPassword());
  }
  
  private boolean camposVacios(){
    if(txtExtension.getText().isEmpty() || txtTelefono.getText().isEmpty() ||
        txtExtension.getText().isEmpty() || txtPassword.getText().isEmpty()){
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
