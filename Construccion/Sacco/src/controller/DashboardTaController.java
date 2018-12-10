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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class DashboardTaController implements Initializable {

  @FXML
  private ImageView btnExit;
  @FXML
  private Label lblTa;
  @FXML
  private Button btnMantenimiento;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnReportes;
  @FXML
  private Button btnSalir;

  private TecnicoAcademico tecnico;

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
  private void registrarMantenimiento(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Responsable.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane dict = loader.getRoot();
    FrameDictamenResponsableController dictamen = loader.getController();
    dictamen.cargarUsuario(tecnico);
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();
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

  void cargarUsuario(TecnicoAcademico user) {
    tecnico = user;
    lblTa.setText(tecnico.getNombre());
  }

}
