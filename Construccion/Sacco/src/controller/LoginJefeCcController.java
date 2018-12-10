/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import dao.JefeCentroComputoDAO;
import model.JefeCentroComputo;
import model.Mensaje;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class LoginJefeCcController implements Initializable {

  @FXML
  private Pane paneJefeCc;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private TextField txtNumPersonal;
  @FXML
  private Button btnIngresar;
  @FXML
  private Button btnBack;
  @FXML
  private AnchorPane anchorPane;

  private JefeCentroComputo usuario = null;

  /**
   * Initializes the controller class.
   * @param url url
   * @param rb rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Nothing
  }

  @FXML
  private void regresarPantalla(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/LoginPrincipal.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane logIn = loader.getRoot();
    Scene newScene = new Scene(logIn);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("LogIn");
    curStage.show();
  }

  @FXML
  private void ingresarDashboardJefeCc(MouseEvent event) throws SQLException {
    if (!validarCampos()) {
      if (!usuarioCorrecto()) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DashboardJefeCC.fxml"));
        try {
          loader.load();
        } catch (IOException ex) {
          Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPane dashboardJcc = loader.getRoot();
        DashboardJefeCcController dashboard = loader.getController();
        dashboard.cargarUsuario(usuario);
        Scene newScene = new Scene(dashboardJcc);
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.setScene(newScene);
        curStage.setTitle("Dashboard Jefe de Centro de Cómputo");
        curStage.show();
      } else {
        Mensaje.mostrarMensaje("Usuario Incorrecto");
      }
    } else {
      Mensaje.mostrarMensaje("Hay campos vacíos");
    }
  }

  public boolean validarCampos() {
    return txtPassword.getText().isEmpty() || txtNumPersonal.getText().isEmpty();
  }

  public boolean usuarioCorrecto() throws SQLException {
    JefeCentroComputo aux = new JefeCentroComputo(txtNumPersonal.getText(), txtPassword.getText());
    usuario = JefeCentroComputoDAO.obtenerJefeCc(aux);
    return (usuario == null);
  }
}
