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
import model.DAO.TecnicoAcademicoDAO;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarTecnicoController implements Initializable {

  @FXML
  private TextField txtNumPersonal;
  @FXML
  private Button btnGuardar;
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
  @FXML
  private AnchorPane anchorPane;

  private TecnicoAcademico tecnico;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void habilitarShowPass(KeyEvent event) {
    lblShowPass.setVisible(true);
  }

  @FXML
  private void mostrarPassword(MouseEvent event) {
    txtPasswordShowing.setVisible(true);
    txtPassword.setVisible(false);
    txtPasswordShowing.setText(txtPassword.getText());
  }

  @FXML
  private void hidePassword(MouseEvent event) {
    txtPasswordShowing.setVisible(false);
    txtPassword.setVisible(true);
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void guardarTecnico(MouseEvent event) throws SQLException {
    if (!camposVacios()) {
      tecnico = new TecnicoAcademico(txtNumPersonal.getText(), txtNombre.getText(),
          txtPassword.getText(), txtTelefono.getText(), txtExtension.getText(), 
          txtCorreo.getText() + "@uv.mx");
      guardarEnBd(tecnico);
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
      mensaje("Hardware guardado con exito en la base de datos");
    } else {
      mensaje("Todos los campos deben estar llenos");
    }
  }

  private boolean camposVacios() {
    if (txtNumPersonal.getText().isEmpty() || txtNombre.getText().isEmpty() || 
        txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty() || 
        txtExtension.getText().isEmpty() || txtPassword.getText().isEmpty()) {
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

  private void guardarEnBd(TecnicoAcademico tecnico) throws SQLException {
    TecnicoAcademicoDAO.guardarTecnico(tecnico);
  }

}
