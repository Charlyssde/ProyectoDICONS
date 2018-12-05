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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Area;
import model.DAO.AreaDAO;
import model.DAO.HardwareDAO;
import model.Hardware;
import model.Responsable;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class Frame_Dictamen_EquipoController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TextField txtBuscar;
  @FXML
  private Button btnSearch;
  @FXML
  private Button btnCancelar;
  @FXML
  private Button btnSiguiente;
  @FXML
  private Label lblDate;
  @FXML
  private Label lblTecnico;

  private TecnicoAcademico tecnico;

  private Responsable responsable;

  private Hardware equipo;
  @FXML
  private TextArea txtObservaciones;

  private final String patron = "[\\d]";
  @FXML
  private TextField txtModelo;
  @FXML
  private TextField txtMarca;
  @FXML
  private TextField txtTipo;
  @FXML
  private TextField txtNumSerie;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  @FXML
  private void buscarEquipo(MouseEvent event) throws SQLException {
    if (txtBuscar.getText().isEmpty()) {
      mensaje("Ingresa un numero de inventario");
    } else if (esNumero()) {
      equipo = HardwareDAO.obtenerHardwareNumInv(Integer.parseInt(txtBuscar.getText()));
      Area area = AreaDAO.obtenerUbicacion(equipo.getUbicacion().getIdUbicacion());
      equipo.setUbicacion(area);
      if (equipo == null) {
        mensaje("El equipo no existe en la base de datos");
      } else if (equipo.getEstado().equals("En Mantenimiento")) {
        mensaje("El equipo ya se encuentra en mantenimiento, no puede realizar esta accion");
      } else {
        llenarCampos(equipo);
        btnSiguiente.setDisable(false);
      }
    } else {
      mensaje("Debes ingresar un numero");
    }
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
    curStage.setTitle("Dictamen de Mantenimiento");
    curStage.show();
  }

  @FXML
  private void siguienteDictamen(MouseEvent event) {
    if (camposVacios()) {
      mensaje("No puedes dejar campos vacios");
    } else {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Dictamen.fxml"));
      try {
        loader.load();
      } catch (IOException ex) {
        Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
      }
      //LoginJefeCCController display = loader.getController();//Utilizar a menos que se haga una accion dentro de la clase que viene
      AnchorPane dict = loader.getRoot();
      Frame_Dictamen_DictamenController dictamen = loader.getController();
      dictamen.cargarResumen(tecnico, responsable, equipo, txtObservaciones.getText());
      Scene newScene = new Scene(dict);
      Stage curStage = (Stage) anchorPane.getScene().getWindow();
      curStage.setScene(newScene);
      curStage.setTitle("Dictamen de Mantenimiento");
      curStage.show();
    }
  }

  void cargarDatos(TecnicoAcademico tecnico, Responsable responsable) {
    this.tecnico = tecnico;
    this.responsable = responsable;
    lblTecnico.setText(tecnico.getNombre());
  }

  private boolean camposVacios() {
    if (txtObservaciones.getText().isEmpty()) {
      return true;
    }
    return false;
  }

  private boolean esNumero() {
    Pattern pattern = Pattern.compile(patron);
    Matcher matcher = pattern.matcher(txtBuscar.getText());
    if (matcher.find()) {
      return true;
    }
    return false;
  }

  private void llenarCampos(Hardware equipo) {
    txtMarca.setText(equipo.getMarca());
    txtModelo.setText(equipo.getModelo());
    txtNumSerie.setText(equipo.getNumSerie());
    txtTipo.setText(equipo.getTipo());
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
