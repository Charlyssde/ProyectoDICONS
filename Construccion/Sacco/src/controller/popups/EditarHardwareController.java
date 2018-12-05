/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.popups;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Area;
import model.DAO.AreaDAO;
import model.DAO.HardwareDAO;
import model.Hardware;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class EditarHardwareController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private ChoiceBox<String> chbTipo;
  @FXML
  private ChoiceBox<String> chbEdificio;
  @FXML
  private TextField txtMarca;
  @FXML
  private TextField txtNumSerie;
  @FXML
  private TextField txtModelo;
  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;

  private Hardware here;

  private ObservableList<Area> areas;

  private String[] ubicaciones;

  private int[] ids;

  private Area area;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    try {
      cargarBox();
    } catch (SQLException ex) {
      Logger.getLogger(EditarHardwareController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void actualizarHardware(MouseEvent event) throws SQLException {
    if (!camposVacios()) {
      seleccionada();
      here = new Hardware(here.getNumInventario(), txtMarca.getText(), txtModelo.getText(),
          txtNumSerie.getText(), here.getEstado(), chbTipo.getValue(), area);
      HardwareDAO.editarHardware(here);
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
      mensaje("Actualizacion exitosa");
    } else {
      mensaje("No se deben dejar campos vacios");
    }
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  public void cargarHardware(Hardware hardware) {
    here = hardware;
    txtMarca.setText(hardware.getMarca());
    txtModelo.setText(hardware.getModelo());
    txtNumSerie.setText(hardware.getNumSerie());
    chbTipo.setValue(hardware.getTipo());
    chbEdificio.setValue(hardware.getUbicacion().toString());

  }

  private void cargarBox() throws SQLException {
    chbTipo.getItems().addAll("Equipo de Computación", "Equipo de Comunicación", "Periférico");
    cargarAreas();
  }

  private boolean camposVacios() {
    if (txtMarca.getText().equals("") || txtModelo.getText().equals("")
        || txtNumSerie.getText().equals("") || chbTipo.getValue().isEmpty()
        || chbEdificio.getValue().isEmpty()) {
      return true;
    }
    return false;
  }

  private void mensaje(String mensaje) {
    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
    dialogo.setTitle("Aviso");
    dialogo.setHeaderText(null);
    dialogo.setContentText(mensaje);
    dialogo.initStyle(StageStyle.UTILITY);
    dialogo.showAndWait();
  }

  private void cargarAreas() throws SQLException {
    areas = (ObservableList<Area>) AreaDAO.obtenerAllAreas();
    ubicaciones = new String[areas.size()];
    ids = new int[areas.size()];
    for (int i = 0; i < areas.size(); i++) {
      ubicaciones[i] = areas.get(i).toString();
      ids[i] = areas.get(i).getIdUbicacion();
      chbEdificio.getItems().add(areas.get(i).toString());
    }
  }

  private void seleccionada() throws SQLException {
    String seleccion = chbEdificio.getValue();
    for (int i = 0; i < ids.length; i++) {
      if (ubicaciones[i].equals(seleccion)) {
        area = AreaDAO.obtenerUbicacion(ids[i]);
      }
    }
  }
}
