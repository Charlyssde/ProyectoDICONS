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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.ResponsableDAO;
import model.Mensaje;
import model.Responsable;

/**
 * FXML Controller class
 *
 * @author texch
 */
public class AgregarResponsableController implements Initializable {

  @FXML
  private AnchorPane anchorPane;
  @FXML
  private TextField txtNumPersonal;
  @FXML
  private TextField txtNombre;
  @FXML
  private TextField txtTelefono;
  @FXML
  private TextField txtExtension;
  @FXML
  private TextField txtCorreo;
  @FXML
  private Button btnGuardar;
  @FXML
  private Button btnCancelar;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Nothing
  }

  @FXML
  private void cancelarRegistro(MouseEvent event) {
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void guardarResponsable(MouseEvent event) throws SQLException {
    if (camposVacios()) {
      Mensaje.mostrarMensaje("No pueden quedar campos vacios");
    } else {
      Responsable nuevo = new Responsable(txtNumPersonal.getText(), txtNombre.getText(),
          txtTelefono.getText(), txtExtension.getText(), txtCorreo.getText());
      ResponsableDAO.agregarResponsable(nuevo);
      Mensaje.mostrarMensaje("Responsable guardado exitosamente");
      Stage stage = (Stage) anchorPane.getScene().getWindow();
      stage.close();
    }
  }

  private boolean camposVacios() {
    return (txtNumPersonal.getText().isEmpty() || txtNombre.getText().isEmpty()
        || txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty()
        || txtExtension.getText().isEmpty());
  }

}
