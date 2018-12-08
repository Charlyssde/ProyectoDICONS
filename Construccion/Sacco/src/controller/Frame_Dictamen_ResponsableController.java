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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.ResponsableDAO;
import model.Responsable;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_Dictamen_ResponsableController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnSearch;
  @FXML
  private TextField txtBuscar;
  @FXML
  private Button btnSiguiente;
  @FXML
  private Button btnCancelar;
  @FXML
  private TextField txtNombre;
  @FXML
  private Label lblDate;
  @FXML
  private TextField txtCorreo;
  @FXML
  private Label lblTecnico;

  private TecnicoAcademico tecnico;

  private Responsable responsable;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void buscarPersonal(MouseEvent event) throws SQLException {
    if (txtBuscar.getText().isEmpty()) {
      mensaje("Escribe un numero de personal");
    } else {
      responsable = ResponsableDAO.obtenerResponsableNumPersonal(txtBuscar.getText());
      if (responsable == null) {
        mensaje("No se encuentra el numero de personal asociado a un responsable");
      } else {
        llenarCampos(responsable);
        btnSiguiente.setDisable(false);
      }
    }

  }

  @FXML
  private void siguienteDictamen(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Equipo.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane dict = loader.getRoot();
    Frame_Dictamen_EquipoController dictamen = loader.getController();
    dictamen.cargarDatos(tecnico, responsable);
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();

  }

  @FXML
  private void cancelarDictamen(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/DashboardTA.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
    AnchorPane dict = loader.getRoot();
    DashboardTAController dashboard = loader.getController();
    dashboard.cargarUsuario(tecnico);
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Tecnico Academico");
    curStage.show();
  }

  public void cargarUsuario(TecnicoAcademico user) {
    this.tecnico = user;
    lblTecnico.setText(tecnico.getNombre());
  }

  private boolean usuarioEncontrado() {
    if (this.responsable == null) {
      return false;
    }

    return true;
  }

  private void llenarCampos(Responsable responsable) {
    txtNombre.setText(responsable.getNombre());
    txtCorreo.setText(responsable.getCorreo());
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
