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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Area;
import dao.AreaDAO;
import dao.HardwareDAO;
import model.Hardware;
import model.Mensaje;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarHardwareController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;
  @FXML
  private TextField txtMarca;
  @FXML
  private ChoiceBox<String> chbEdificio;
  @FXML
  private TextField txtNumSerie;
  @FXML
  private TextField txtModelo;
  @FXML
  private ChoiceBox<String> chbTipo;

  String[] ubicaciones;

  int[] ids;

  Area area;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    try {
      cargarBox();
    } catch (SQLException ex) {
      Logger.getLogger(AgregarHardwareController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void guardarHardware(MouseEvent event) throws SQLException {
    if (!camposVacios()) {
      seleccionada();
      Hardware hardware = new Hardware(null, txtMarca.getText(), txtModelo.getText(),
          txtNumSerie.getText(), "Dispo", chbTipo.getValue(), area);
      guardarEnBd(hardware);
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
      Mensaje.mostrarMensaje("Hardware guardado con exito en la base de datos");
    } else {
      Mensaje.mostrarMensaje("Todos los campos deben estar llenos");
    }

  }

  private void guardarEnBd(Hardware hardware) {
    HardwareDAO.agregarHardware(hardware);
  }

  private void cargarBox() throws SQLException {
    chbTipo.getItems().addAll("Equipo de Computación", "Equipo de Comunicación", "Periférico");
    cargarAreas();
  }

  private void cargarAreas() throws SQLException {
    ObservableList<Area> areas = (ObservableList<Area>) AreaDAO.obtenerAllAreas();
    ubicaciones = new String[areas.size()];
    ids = new int[areas.size()];
    for (int i = 0; i < areas.size(); i++) {
      ubicaciones[i] = areas.get(i).toString();
      ids[i] = areas.get(i).getIdUbicacion();
      chbEdificio.getItems().add(areas.get(i).toString());
    }
  }

  private boolean camposVacios() {
    return (txtMarca.getText().equals("") || txtModelo.getText().equals("")
        || txtNumSerie.getText().equals("") || chbTipo.getValue().isEmpty() 
        || chbEdificio.getValue().isEmpty());
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
