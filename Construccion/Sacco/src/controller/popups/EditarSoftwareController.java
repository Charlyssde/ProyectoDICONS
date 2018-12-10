/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.popups;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.SoftwareDAO;
import model.Mensaje;
import model.Software;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class EditarSoftwareController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TextField txtSoftware;
  @FXML
  private TextArea txtObservaciones;
  @FXML
  private TextField txtVersion;
  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;

  private Software software;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Nothing
  }

  @FXML
  private void actualizarSoftware(MouseEvent event) throws SQLException {
    if (!camposVacios()) {
      Software here = new Software(software.getNumInventario(), txtSoftware.getText(),
          software.getNumLicencias(), software.getFechaAdquisicion(), txtVersion.getText(),
          txtObservaciones.getText());
      guardarEnBd(here);
      Mensaje.mostrarMensaje("Software actualizado con exito");
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
    } else {
      Mensaje.mostrarMensaje("Ningun campo puede quedar vacio");
    }
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  public void cargarSoftware(Software software) {
    this.software = software;
    txtObservaciones.setText(software.getObservaciones());
    txtSoftware.setText(software.getNombre());
    txtVersion.setText(software.getVersion());
  }

  public boolean camposVacios() {
    return (txtObservaciones.getText().isEmpty() || txtSoftware.getText().isEmpty()
        || txtVersion.getText().isEmpty());
  }

  public void guardarEnBd(Software nuevo) throws SQLException {
    SoftwareDAO.editarSoftware(nuevo);
  }

}
