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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DAO.DictamenDAO;
import model.DAO.HardwareDAO;
import model.Dictamen;
import model.Hardware;
import model.Responsable;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_Dictamen_DictamenController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Label lblTecnico;
  @FXML
  private Label lblDate;
  @FXML
  private ChoiceBox<String> chbTipo;
  @FXML
  private TextArea txtDescripcion;
  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;
  @FXML
  private TextField txtSolicitante;
  @FXML
  private TextField txtEquipo;

  private TecnicoAcademico tecnico;

  private Hardware equipo;

  private Responsable solicitante;

  private String observaciones;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    cargarBox();
  }

  void cargarResumen(TecnicoAcademico tecnico, Responsable responsable, Hardware equipo, String observaciones) {
    this.tecnico = tecnico;
    this.solicitante = responsable;
    this.equipo = equipo;
    txtSolicitante.setText(solicitante.getNombre());
    txtEquipo.setText(equipo.getNumSerie());
    lblTecnico.setText(tecnico.getNombre());
    this.observaciones = observaciones;
    btnGuardar.setDisable(false);
  }

  @FXML
  private void siguienteDictamen(MouseEvent event) throws SQLException {
    if (camposVacios()) {
      mensaje("No pueden quedar campos vacios");
    } else {
      equipo.setEstado("En Mantenimiento");
      HardwareDAO.editarHardwareFromManten(equipo);
      Hardware hw = HardwareDAO.obtenerHardwareNumInv(equipo.getNumInventario());
      Dictamen dictamen = new Dictamen(txtDescripcion.getText(), chbTipo.getValue(),
          observaciones, tecnico, solicitante, hw);
      DictamenDAO.guardarDictamen(dictamen);

      mensaje("Dictamen guardado con exito");
      regresarADashboard();
    }
  }

  @FXML
  private void cancelarDictamen(MouseEvent event) {
    regresarADashboard();
  }

  private void cargarBox() {
    chbTipo.getItems().addAll("Boorado simple", "Borrado permanente");
  }

  private boolean camposVacios() {
    if (chbTipo.getValue() == null || txtDescripcion.getText().isEmpty()) {
      return true;
    }
    return false;
  }

  private void regresarADashboard() {
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

  private void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }
}
