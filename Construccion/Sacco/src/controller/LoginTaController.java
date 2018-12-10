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
import javafx.stage.Stage;
import dao.TecnicoAcademicoDAO;
import model.Mensaje;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class LoginTaController implements Initializable {

  @FXML
  private Button btnIngresar;
  @FXML
  private Button btnBack;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TextField txtNumPersonal;
  @FXML
  private PasswordField txtPassword;

  private TecnicoAcademico usuario = null;

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
  private void ingresarDashboardTa(MouseEvent event) throws SQLException {
    if (!validarCampos()) {
      if (!usuarioCorrecto()) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DashboardTA.fxml"));
        try {
          loader.load();
        } catch (IOException ex) {
          Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPane dashboardTa = loader.getRoot();
        DashboardTaController dash = loader.getController();
        dash.cargarUsuario(usuario);
        Scene newScene = new Scene(dashboardTa);
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.setScene(newScene);
        curStage.setTitle("Dashboard Técnico Académico");
        curStage.show();
      } else {
        Mensaje.mostrarMensaje("El usuario no existe");
      }
    } else {
      Mensaje.mostrarMensaje("Hay campos vacíos");
    }
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

  private boolean validarCampos() {
    return (txtPassword.getText().equals("") || txtNumPersonal.getText().equals(""));
  }

  private boolean usuarioCorrecto() throws SQLException {
    TecnicoAcademico ta = new TecnicoAcademico(txtNumPersonal.getText(), txtPassword.getText());
    usuario = TecnicoAcademicoDAO.obtenerTecnico(ta);
    return (usuario == null);
  }

}
