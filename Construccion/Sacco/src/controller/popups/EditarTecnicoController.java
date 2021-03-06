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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.TecnicoAcademicoDAO;
import model.Mensaje;
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
    // Nothing
  }

  @FXML
  private void actualizarTecnico(MouseEvent event) throws SQLException {
    if (camposVacios()) {
      Mensaje.mostrarMensaje("No se pueden quedar campos vacios");
    } else {
      TecnicoAcademico nuevo = new TecnicoAcademico(
          tecnico.getNumPersonal(), tecnico.getNombre(), txtPassword.getText(),
          txtTelefono.getText(), txtExtension.getText(), tecnico.getCorreoInstitucional());
      TecnicoAcademicoDAO.editarTecnicoAcademico(nuevo);
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
      Mensaje.mostrarMensaje("Tecnico actualizado correctamente");
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

  public void cargarTecnico(TecnicoAcademico nuevo) {
    this.tecnico = nuevo;
    txtNumPersonal.setText(tecnico.getNumPersonal());
    txtNombre.setText(tecnico.getNombre());
    txtCorreo.setText(tecnico.getCorreoInstitucional());
    txtTelefono.setText(tecnico.getTelefono());
    txtExtension.setText(tecnico.getExtension());
    txtPassword.setText(tecnico.getPassword());
  }

  private boolean camposVacios() {
    return (txtExtension.getText().isEmpty() || txtTelefono.getText().isEmpty()
        || txtPassword.getText().isEmpty());
  }

}
