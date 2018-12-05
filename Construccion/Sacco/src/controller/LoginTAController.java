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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
public class LoginTAController implements Initializable {

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
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void ingresarDashboardTa(MouseEvent event) throws SQLException {
    if (validarCampos()) {
      if (usuarioCorrecto()) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DashboardTA.fxml"));
        try {
          loader.load();
        } catch (IOException ex) {
          Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
        AnchorPane dashboardTa = loader.getRoot();
        DashboardTAController dash = loader.getController();
        dash.cargarUsuario(usuario);
        Scene newScene = new Scene(dashboardTa);
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.setScene(newScene);
        curStage.setTitle("Dashboard Técnico Académico");
        curStage.show();
      } else {
        mensajeError("Usuario Incorrecto");
      }
    } else {
      mensajeError("Hay campos vacíos");
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
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane logIn = loader.getRoot();
    Scene newScene = new Scene(logIn);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("LogIn");
    curStage.show();
  }

  private boolean validarCampos() {
    if (txtPassword.getText().equals("") || txtNumPersonal.getText().equals("")) {
      return false;
    }
    return true;
  }

  private boolean usuarioCorrecto() throws SQLException {
    TecnicoAcademico ta = new TecnicoAcademico(txtNumPersonal.getText(), txtPassword.getText());
    usuario = TecnicoAcademicoDAO.obtenerTecnico(ta);
    if (usuario != null) {
      return true;
    }
    return false;
  }

  private void mensajeError(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }
}
