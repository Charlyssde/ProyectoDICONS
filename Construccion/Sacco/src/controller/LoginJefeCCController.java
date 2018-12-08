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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.JefeCentroComputoDAO;
import model.JefeCentroComputo;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class LoginJefeCCController implements Initializable {

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

  //JefeCentroComputo user;
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
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

  @FXML
  private void ingresarDashboardJefeCc(MouseEvent event) throws SQLException {
    if (validarCampos()) {
      if (usuarioCorrecto()) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DashboardJefeCC.fxml"));
        try {
          loader.load();
        } catch (IOException ex) {
          Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AnchorPane dashboardJcc = loader.getRoot();
        DashboardJefeCCController dashboard = loader.getController();
        dashboard.cargarUsuario(usuario);
        Scene newScene = new Scene(dashboardJcc);
        Stage curStage = (Stage) anchorPane.getScene().getWindow();
        curStage.setScene(newScene);
        curStage.setTitle("Dashboard Jefe de Centro de Cómputo");
        curStage.show();
      } else {
        mensajeError("Usuario Incorrecto");
      }
    } else {
      mensajeError("Hay campos vacíos");
    }
  }

  public boolean validarCampos() {
    if (txtPassword.getText().equals("") || txtNumPersonal.getText().equals("")) {
      return false;
    }
    return true;
  }

  public boolean usuarioCorrecto() throws SQLException {

    JefeCentroComputo aux = new JefeCentroComputo(txtNumPersonal.getText(), txtPassword.getText());
    usuario = JefeCentroComputoDAO.obtenerJefeCc(aux);
    if (usuario == null) {
      return false;
    }
    System.out.println("Usuario: " + usuario.getNombre());
    return true;
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
