package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Area;
import dao.AreaDAO;
import dao.HardwareDAO;
import model.Hardware;
import model.Mensaje;
import model.Responsable;
import model.TecnicoAcademico;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class FrameDictamenEquipoController implements Initializable {

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

  private Hardware equipo = null;
  @FXML
  
  private TextArea txtObservaciones;
  
  private static final String PATRON = "[\\d]";
  
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
   * @param url url
   * @param rb rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    Date fecha = new Date();
    lblDate.setText(fecha.toString());
  }
  
  @FXML
  private void buscarEquipo(MouseEvent event) throws SQLException {
    if (txtBuscar.getText().isEmpty()) {
      Mensaje.mostrarMensaje("Ingresa un numero de inventario");
    } else if (!esNumero()) {
      Mensaje.mostrarMensaje("Debes ingresar un numero");
    } else {
      equipo = HardwareDAO.obtenerHardwareNumInv(Integer.parseInt(txtBuscar.getText()));
      if (equipo != null) {
        Area area = AreaDAO.obtenerUbicacion(equipo.getUbicacion().getIdUbicacion());
        equipo.setUbicacion(area);
        if (equipo.getEstado().equals("En Mantenimiento")) {
          Mensaje.mostrarMensaje("El equipo ya se encuentra en mantenimiento, "
              + "no puede realizar esta accion");
        } else if (equipo.getNumInventario() == null) {
          Mensaje.mostrarMensaje("No existe el equipo");
        } else {
          llenarCampos(equipo);
          btnSiguiente.setDisable(false);
        }
      } else {
        Mensaje.mostrarMensaje("El equipo no existe");
      }
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
    AnchorPane dict = loader.getRoot();
    DashboardTaController dashboard = loader.getController();
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
      Mensaje.mostrarMensaje("No puedes dejar campos vacios");
    } else {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/view/Frame_Dictamen_Dictamen.fxml"));
      try {
        loader.load();
      } catch (IOException ex) {
        Logger.getLogger(LoginPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
      }
      AnchorPane dict = loader.getRoot();
      FrameDictamenDictamenController dictamen = loader.getController();
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
    return (txtObservaciones.getText().isEmpty());
  }

  private boolean esNumero() {
    Pattern pattern = Pattern.compile(PATRON);
    Matcher matcher = pattern.matcher(txtBuscar.getText());
    return (matcher.find());
  }

  private void llenarCampos(Hardware equipo) {
    txtMarca.setText(equipo.getMarca());
    txtModelo.setText(equipo.getModelo());
    txtNumSerie.setText(equipo.getNumSerie());
    txtTipo.setText(equipo.getTipo());
  }

}
