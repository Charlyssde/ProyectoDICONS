package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.JefeCentroComputo;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class DashboardJefeCcController implements Initializable {

  @FXML
  private Label lblJefeCc;
  @FXML
  private Button btnResguardos;
  @FXML
  private Button btnHardware;
  @FXML
  private Button btnSoftware;
  @FXML
  private Button btnReportes;
  @FXML
  private Button btnEstadisticas;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnSalir;
  @FXML
  private Button btnResponsables;
  @FXML
  private Button btnTecnicos;
  
  private JefeCentroComputo jefe;

  /**
   * Initializes the controller class.
   * @param url url
   * @param rb rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Nothing
  }

  @FXML
  private void salirDashboard(MouseEvent event) {
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

  void cargarUsuario(JefeCentroComputo jefe) {
    this.jefe = jefe;
    lblJefeCc.setText(jefe.getNombre());
    //To change body of generated methods, choose Tools | Templates.
  }

  @FXML
  private void administrarHardware(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Hardware.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane adminHardware = loader.getRoot();
    FrameHardwareController frameHw = loader.getController();
    frameHw.cargarUsuario(jefe);
    Scene newScene = new Scene(adminHardware);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Administración de Hardware");
    curStage.show();
  }

  @FXML
  private void administrarSoftware(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Software.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane adminSoftware = loader.getRoot();
    FrameSoftwareController frameSw = loader.getController();
    frameSw.cargarUsuario(jefe);
    Scene newScene = new Scene(adminSoftware);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Administración de Software");
    curStage.show();
  }

  @FXML
  private void administrarResponsables(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Responsables.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane adminSoftware = loader.getRoot();
    FrameResponsablesController frameResp = loader.getController();
    frameResp.cargarUsuario(jefe);
    Scene newScene = new Scene(adminSoftware);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Administración de Responsables");
    curStage.show();
  }

  @FXML
  private void administrarTecnicosAcademicos(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Usuarios.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane adminSoftware = loader.getRoot();
    FrameUsuariosController frameTa = loader.getController();
    frameTa.cargarUsuario(jefe);
    Scene newScene = new Scene(adminSoftware);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Administración de Técnicos");
    curStage.show();
  }

}
