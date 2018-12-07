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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
  private TextField txtDireccion;
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

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void actualizarTecnico(MouseEvent event) {
    try {
      Parent sc = FXMLLoader.load(getClass().getResource("/view/messages/ExitoMessage.fxml"));
      Scene nu = new Scene(sc);
      Stage stage = new Stage();
      stage.setTitle("Éxito");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setResizable(false);
      stage.setScene(nu);
      stage.show();
      Stage thisStage = (Stage) anchorPane.getScene().getWindow();
      thisStage.close();
    } catch (IOException e) {
      e.printStackTrace();
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

}
