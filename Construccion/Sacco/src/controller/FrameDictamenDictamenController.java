package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.DictamenDAO;
import dao.HardwareDAO;
import model.Dictamen;
import model.Hardware;
import model.Mensaje;
import model.Responsable;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class FrameDictamenDictamenController implements Initializable {

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
   * @param url url
   * @param rb rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    Date fecha = new Date();
    lblDate.setText(fecha.toString());
    cargarBox();
  }

  void cargarResumen(TecnicoAcademico tecnico, Responsable responsable, 
      Hardware equipo, String observaciones) {
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
      Mensaje.mostrarMensaje("No pueden quedar campos vacios");
    } else {
      equipo.setEstado("En Mantenimiento");
      HardwareDAO.editarHardwareFromManten(equipo);
      Hardware hw = HardwareDAO.obtenerHardwareNumInv(equipo.getNumInventario());
      Dictamen dictamen = new Dictamen(txtDescripcion.getText(), chbTipo.getValue(),
          observaciones, tecnico, solicitante, hw);
      DictamenDAO.guardarDictamen(dictamen);

      Mensaje.mostrarMensaje("Dictamen guardado con exito");
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
    return (chbTipo.getValue() == null || txtDescripcion.getText().isEmpty());
  }

  private void regresarADashboard() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/DashboardTA.fxml"));
    try {
      loader.load();
    } catch (IOException ex) {
      Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    AnchorPane dict = loader.getRoot();
    DashboardTaController dashboard = loader.getController();
    dashboard.cargarUsuario(tecnico);
    Scene newScene = new Scene(dict);
    Stage curStage = (Stage) anchorPane.getScene().getWindow();
    curStage.setScene(newScene);
    curStage.setTitle("Dashboard Tecnico Academico");
    curStage.show();
  }

}
